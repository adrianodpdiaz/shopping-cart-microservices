package com.adpd.cart.controller;

import com.adpd.cart.resource.dto.CartDTO;
import com.adpd.cart.resource.form.CreateCartForm;
import com.adpd.cart.service.CartService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;

import static com.adpd.cart.util.CartTestUtil.mockCartDTO;
import static com.adpd.cart.util.CartTestUtil.mockCreateCartForm;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CartController.class)
class CartControllerTest {

    private static final String URL_CART = "/api/v1/carts/1";
    private static final String URL_CARTS = "/api/v1/carts";

    @Inject
    private MockMvc mockMvc;
    @Inject
    private Gson gson;
    @MockBean
    private CartService cartService;


    @Test
    void shouldCreateACart() throws Exception {
        CreateCartForm createCartForm = mockCreateCartForm();
        String payload = gson.toJson(createCartForm);

        when(cartService.createCart(any())).thenReturn(1L);

        mockMvc.perform(post(URL_CARTS).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$").doesNotExist());
    }


    @Test
    void shouldGetACart() throws Exception {
        CartDTO cartDTO = mockCartDTO();

        when(cartService.getCart(any())).thenReturn(cartDTO);

        mockMvc.perform(get(URL_CART).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customerId").value(cartDTO.getCustomerId()))
            .andExpect(jsonPath("$.createdAt")
                .value(cartDTO.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
            .andExpect(jsonPath("$.items[0].productId").value(cartDTO.getItems().get(0).getProductId()))
            .andExpect(jsonPath("$.items[0].unitPrice").value(cartDTO.getItems().get(0).getUnitPrice()))
            .andExpect(jsonPath("$.items[0].quantity").value(cartDTO.getItems().get(0).getQuantity()))
            .andExpect(jsonPath("$.items[1].productId").value(cartDTO.getItems().get(1).getProductId()))
            .andExpect(jsonPath("$.items[1].unitPrice").value(cartDTO.getItems().get(1).getUnitPrice()))
            .andExpect(jsonPath("$.items[1].quantity").value(cartDTO.getItems().get(1).getQuantity()));

    }

    @Test
    void testCreateCartFormValidation() throws Exception {
        CreateCartForm createCartForm = new CreateCartForm();
        createCartForm.setCustomerId(-1L);
        String payload = gson.toJson(createCartForm);

        when(cartService.createCart(createCartForm)).thenReturn(1L);

        mockMvc.perform(post(URL_CARTS).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors", hasItem("customerId must be positive")));
    }
}
