package com.adpd.product.service;

import com.adpd.product.entity.Product;
import com.adpd.product.mapping.ProductMapperImpl;
import com.adpd.product.repository.ProductRepository;
import com.adpd.product.resource.form.RegisterProductForm;
import com.adpd.feignclients.resource.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.adpd.product.util.ProductTestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapperImpl productMapper;
    @InjectMocks
    private ProductService productService;


    @Test
    void shouldRegisterFirstProduct() {
        RegisterProductForm registerProductForm = mockRegisterProductForm();
        Product product = mockProduct(1L);

        when(productRepository.findMaxIdByCategory(registerProductForm.getCategory()))
            .thenReturn(Optional.empty());
        when(productMapper.requestToEntity(any())).thenReturn(product);
        when(productRepository.saveAndFlush(product)).thenReturn(product);

        Long productId = productService.registerProduct(registerProductForm);
        assertEquals("CA2267T1", product.getSku());
        assertEquals(registerProductForm.getName(), product.getName());
        assertEquals(registerProductForm.getCategory(), product.getCategory());
        assertEquals(registerProductForm.getDescription(), product.getDescription());
        assertEquals(registerProductForm.getPrice(), product.getPrice());
        assertEquals(1L, productId);
    }

    @Test
    void shouldRegisterAProduct() {
        RegisterProductForm registerProductForm = mockRegisterProductForm();
        Product product = mockProduct(2L);
        Product lastIdProduct = new Product();
        lastIdProduct.setId(1L);

        when(productRepository.findMaxIdByCategory(registerProductForm.getCategory()))
            .thenReturn(Optional.of(lastIdProduct));
        when(productMapper.requestToEntity(registerProductForm)).thenReturn(product);
        when(productRepository.saveAndFlush(product)).thenReturn(product);

        Long productId = productService.registerProduct(registerProductForm);
        assertEquals("CA2267T2", product.getSku());
        assertEquals(registerProductForm.getName(), product.getName());
        assertEquals(registerProductForm.getCategory(), product.getCategory());
        assertEquals(registerProductForm.getDescription(), product.getDescription());
        assertEquals(registerProductForm.getPrice(), product.getPrice());
        assertEquals(2L, productId);
    }

    @Test
    void shouldGetAProduct() {
        Long productId = 1L;
        Product product = mockProduct(productId);
        ProductDTO mockProductDTO = mockProductDTO();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(mockProductDTO);

        ProductDTO productDTO = productService.getProduct(1L);
        assertEquals(mockProductDTO.getSku(), productDTO.getSku());
        assertEquals(mockProductDTO.getName(), productDTO.getName());
        assertEquals(mockProductDTO.getCategory(), productDTO.getCategory());
        assertEquals(mockProductDTO.getDescription(), productDTO.getDescription());
        assertEquals(mockProductDTO.getPrice(), productDTO.getPrice());
    }
}
