package com.adpd.cart.service;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.entity.Item;
import com.adpd.cart.mapping.CartMapper;
import com.adpd.cart.repository.CartRepository;
import com.adpd.cart.repository.ItemRepository;
import com.adpd.cart.resource.form.CartItemForm;
import com.adpd.cart.resource.form.CreateCartForm;
import com.adpd.cart.resource.dto.CartDTO;
import com.adpd.feignclients.client.CustomerClient;
import com.adpd.feignclients.client.ProductClient;
import com.adpd.feignclients.resource.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartMapper cartMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Long createCart(CreateCartForm createCartForm) {
        // validate customer
        customerClient.getCustomer(createCartForm.getCustomerId());
        // validate products
        List<ProductDTO> products = productClient.listProducts().getBody();
        if(products == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Products not found");
        }

        List<Long> itemProductIds = createCartForm.getItems().stream()
            .map(CartItemForm::getProductId)
            .toList();
        if (!itemProductIds.stream()
            .allMatch(itemId -> products.stream()
                .anyMatch(product -> product.getId().equals(itemId)))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more products not found");
        }

        Cart cart = cartMapper.requestToEntity(createCartForm);
        // set cartItem price based on product's price
        cart.getItems().forEach(cartItem ->
            products.stream()
                .filter(product -> product.getId().equals(cartItem.getProductId()))
                .findFirst()
                .ifPresent(product -> cartItem.setUnitPrice(product.getPrice()))
        );

        cart.getItems().forEach(item -> item.setCart(cart));
        cartRepository.saveAndFlush(cart);
        return cart.getId();
    }

    public CartDTO getCart(Long id) {
        Cart cart = findCartEntity(id);
        return cartMapper.toDTO(cart);
    }

    public void addItem(Long cartId, CartItemForm cartItemForm) {
        Cart cart = findCartEntity(cartId);
        // validate product
        ProductDTO product = productClient.getProduct(cartItemForm.getProductId()).getBody();
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        cart.getItems().stream()
                .filter(cartItem -> product.getId().equals(cartItem.getProductId()))
                .findFirst()
                .ifPresentOrElse(cartItem -> cartItem.setQuantity(cartItem.getQuantity() + cartItemForm.getQuantity()),
                    () -> {
                        Item item = cartMapper.toItem(cartItemForm);
                        item.setUnitPrice(product.getPrice());
                        item.setCart(cart);
                        itemRepository.saveAndFlush(item);
                    }
                );
    }

    public void removeItem(Long cartId, Long productId, Integer quantity) {
        Cart cart = findCartEntity(cartId);
        ProductDTO product = productClient.getProduct(productId).getBody();
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        cart.getItems().stream()
            .filter(cartItem -> product.getId().equals(cartItem.getProductId()))
            .findFirst()
            .ifPresentOrElse(cartItem -> {
                    cartItem.setQuantity(cartItem.getQuantity() - quantity);
                    if (cartItem.getQuantity() <= 0) {
                        cart.getItems().remove(cartItem);
                    }
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not present in the cart");
                }
            );
    }

    private Cart findCartEntity(Long id) {
        return cartRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Register not found for cart id: %d", id)));
    }
}
