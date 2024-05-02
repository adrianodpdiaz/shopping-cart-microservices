package com.adpd.product.mapping;

import com.adpd.product.entity.Product;
import com.adpd.product.resource.dto.ProductDTO;
import com.adpd.product.resource.form.RegisterProductForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.adpd.product.util.ProductTestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    @InjectMocks
    private ProductMapperImpl productMapper;

    @Test
    void testNullMappings() {
        Product product = productMapper.requestToEntity(null);
        ProductDTO productDTO = productMapper.toDTO(null);

        assertNull(product);
        assertNull(productDTO);
    }

    @Test
    void testMappingFormToEntity() {
        RegisterProductForm registerProductForm = mockRegisterProductForm();

        Product product = productMapper.requestToEntity(registerProductForm);

        assertEquals(registerProductForm.getName(), product.getName());
        assertEquals(registerProductForm.getCategory(), product.getCategory());
        assertEquals(registerProductForm.getDescription(), product.getDescription());
        assertEquals(registerProductForm.getPrice(), product.getPrice());
    }

    @Test
    void testMappingEntityToDTO() {
        Product product = mockProduct(1L);
        product.setSku(PRODUCT_SKU);

        ProductDTO productDTO = productMapper.toDTO(product);

        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getSku(), productDTO.getSku());
        assertEquals(product.getCategory(), productDTO.getCategory());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPrice(), productDTO.getPrice());
    }
}
