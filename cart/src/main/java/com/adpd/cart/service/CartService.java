package com.adpd.cart.service;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.mapping.CartMapper;
import com.adpd.cart.repository.CartRepository;
import com.adpd.cart.resource.form.CreateCartForm;
import com.adpd.cart.resource.dto.CartDTO;
import com.adpd.feignclients.client.CustomerClient;
import com.adpd.feignclients.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Long createCart(CreateCartForm createCartForm) {
        // validate customer
        customerClient.getCustomer(createCartForm.getCustomerId());
        // validate products
        createCartForm.getItems().forEach(formItem -> productClient.getProduct(formItem.getProductId()));

        Cart cart = cartMapper.requestToEntity(createCartForm);
        cart.getItems().forEach(item -> item.setCart(cart));
        cartRepository.saveAndFlush(cart);

        return cart.getId();
    }

    public CartDTO getCart(Long id) {
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Register not found for cart id: %d", id)));
        return cartMapper.toDTO(cart);
    }
}
