package com.adpd.cart.service;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.mapping.CartMapperImpl;
import com.adpd.cart.repository.CartRepository;
import com.adpd.cart.resource.dto.CartDTO;
import com.adpd.cart.resource.form.CreateCartForm;
import com.adpd.feignclients.customer.client.CustomerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.adpd.cart.util.CartTestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartMapperImpl cartMapper;
    @Mock
    private CustomerClient customerClient;
    @InjectMocks
    private CartService cartService;


    @Test
    void shouldCreateACart() {
        CreateCartForm createCartForm = mockCreateCartForm();
        Cart cart = mockCart(1L);

        when(customerClient.getCustomer(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(cartMapper.requestToEntity(any())).thenReturn(cart);
        when(cartRepository.saveAndFlush(cart)).thenReturn(cart);

        Long cartId = cartService.createCart(createCartForm);
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
        assertEquals(1L, cartId);
    }

    @Test
    void shouldGetACart() {
        Long cartId = 1L;
        Cart cart = mockCart(cartId);
        CartDTO mockCartDTO = mockCartDTO();

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartMapper.toDTO(cart)).thenReturn(mockCartDTO);

        CartDTO cartDTO = cartService.getCart(cartId);
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
