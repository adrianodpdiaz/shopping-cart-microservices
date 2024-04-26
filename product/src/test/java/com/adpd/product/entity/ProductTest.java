package com.adpd.product.entity;

import com.adpd.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndFindProduct() {
        Product product = new Product();
        product.setName("name");
        product.setCategory("general");
        product.setDescription("product description");
        product.setPrice(new BigDecimal("9.99"));

        productRepository.save(product);
        Long id = product.getId();
        Product savedProduct = productRepository.findById(id).orElseThrow();

        assertEquals(id, savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getCategory(), savedProduct.getCategory());
        assertEquals(product.getDescription(), savedProduct.getDescription());
        assertEquals(product.getPrice(), savedProduct.getPrice());
    }
}
