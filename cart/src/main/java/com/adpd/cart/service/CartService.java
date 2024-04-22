package com.adpd.cart.service;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.mapping.CartMapper;
import com.adpd.cart.repository.CartRepository;
import com.adpd.cart.resource.inbound.CreateCartInbound;
import com.adpd.cart.resource.outbound.CartDTO;
import com.adpd.feignclients.customer.CustomerClient;
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

    public Long createCart(CreateCartInbound createCartInbound) {
        // validate customer
        customerClient.getCustomer(createCartInbound.getCustomerId());

        Cart cart = cartMapper.requestToEntity(createCartInbound);
        cart.getItems().forEach(item -> item.setCart(cart));
        try {
            cartRepository.saveAndFlush(cart);
        } catch (Exception ex) {
            if(ex.getLocalizedMessage().contains("duplicate key value")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Constraint validation error, productId must be unique");
            }
            throw ex;
        }

        return cart.getId();
    }

    public CartDTO getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Register not found for cart id: %d", id)));
        return cartMapper.toDTO(cart);
    }
}
