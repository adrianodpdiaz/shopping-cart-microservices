package com.adpd.cart.entity;

import com.adpd.cart.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.adpd.cart.util.CartTestUtil.mockCart;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CartTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    void shouldSaveAndFindCart() {
        Cart cart = mockCart(null);
        cart.getItems().forEach(item -> {
            item.setId(null);
            item.setCart(cart);
        });

        cartRepository.saveAndFlush(cart);
        Long id = cart.getId();
        Cart newCart = cartRepository.findById(id).orElseThrow();

        assertEquals(id, newCart.getId());
        assertEquals(cart.getCustomerId(), newCart.getCustomerId());
        assertEquals(cart.getCreatedAt(), newCart.getCreatedAt());
        assertEquals(cart.getItems(), newCart.getItems());
    }
}
