package com.adpd.cart.mapping;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.resource.dto.CartDTO;
import com.adpd.cart.resource.form.CreateCartForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.adpd.cart.util.CartTestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CartMapperTest {

    @InjectMocks
    private CartMapperImpl cartMapper;

    @Test
    void testNullMappings() {
        Cart cart = cartMapper.requestToEntity(null);
        CartDTO cartDTO = cartMapper.toDTO(null);

        assertNull(cart);
        assertNull(cartDTO);
    }

    @Test
    void testMappingFormToEntity() {
        CreateCartForm createCartForm = mockCreateCartForm();
        Cart cart = cartMapper.requestToEntity(createCartForm);

        assertEquals(createCartForm.getCustomerId(), cart.getCustomerId());
        assertEquals(createCartForm.getItems().size(), cart.getItems().size());
        assertTrue(createCartForm.getItems().stream()
            .allMatch(formItem -> cart.getItems().stream()
                .filter(cartItem -> cartItem.getProductId().equals(formItem.getProductId()))
                .findFirst()
                .map(cartItem -> {
                    assertEquals(formItem.getUnitPrice(), cartItem.getUnitPrice());
                    assertEquals(formItem.getQuantity(), cartItem.getQuantity());
                    return true;
                })
                .orElse(false)));
    }

    @Test
    void testMappingEntityToDTO() {
        Cart cart = mockCart(1L);
        CartDTO cartDTO = cartMapper.toDTO(cart);

        assertEquals(cart.getCustomerId(), cartDTO.getCustomerId());
        assertEquals(cart.getCreatedAt(), cartDTO.getCreatedAt());
        assertEquals(cart.getItems().size(), cartDTO.getItems().size());
        assertTrue(cart.getItems().stream()
            .allMatch(cartItem -> cartDTO.getItems().stream()
                .filter(dtoItem -> dtoItem.getProductId().equals(cartItem.getProductId()))
                .findFirst()
                .map(dtoItem -> {
                    assertEquals(cartItem.getUnitPrice(), dtoItem.getUnitPrice());
                    assertEquals(cartItem.getQuantity(), dtoItem.getQuantity());
                    return true;
                })
                .orElse(false)));
    }
}
