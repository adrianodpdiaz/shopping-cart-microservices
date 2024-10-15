package com.adpd.product.controller;

import com.adpd.feignclients.resource.dto.ProductDTO;
import com.adpd.product.resource.form.RegisterProductForm;
import com.adpd.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.adpd.product.util.ProductTestUtil.mockProductDTO;
import static com.adpd.product.util.ProductTestUtil.mockRegisterProductForm;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    private static final String URL_PRODUCT = "/api/v1/products/1";
    private static final String URL_PRODUCTS = "/api/v1/products";

    @Inject
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Inject
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterAProduct() throws Exception {
        RegisterProductForm registerProductForm = mockRegisterProductForm();
        String payload = objectMapper.writeValueAsString(registerProductForm);

        when(productService.registerProduct(registerProductForm)).thenReturn(1L);

        mockMvc.perform(post(URL_PRODUCTS).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").doesNotExist());
    }


    @Test
    void shouldGetAProduct() throws Exception {
        ProductDTO productDTO = mockProductDTO();

        when(productService.getProduct(any())).thenReturn(productDTO);

        mockMvc.perform(get(URL_PRODUCT).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value(productDTO.getName()))
                .andExpect(jsonPath("$.description").value(productDTO.getDescription()))
                .andExpect(jsonPath("$.SKU").value(productDTO.getSku()))
                .andExpect(jsonPath("$.category").value(productDTO.getCategory()))
                .andExpect(jsonPath("$.price").value(productDTO.getPrice()));
    }

    @Test
    void testRegisterProductFormValidation() throws Exception {
        RegisterProductForm registerProductForm = new RegisterProductForm();
        String payload = objectMapper.writeValueAsString(registerProductForm);

        when(productService.registerProduct(registerProductForm)).thenReturn(1L);

        mockMvc.perform(post(URL_PRODUCTS).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasItem("description is required")))
                .andExpect(jsonPath("$.errors", hasItem("price is required")))
                .andExpect(jsonPath("$.errors", hasItem("product name is required")))
                .andExpect(jsonPath("$.errors", hasItem("category is required")));
    }
}
