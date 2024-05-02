package com.adpd.product.entity;

import com.adpd.product.repository.ProductRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static com.adpd.product.util.ProductTestUtil.PRODUCT_SKU;
import static com.adpd.product.util.ProductTestUtil.mockProduct;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductTest {

    private Validator validator;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void shouldSaveAndFindProduct() {
        Product mockProduct = mockProduct(null);
        mockProduct.setSku(PRODUCT_SKU);

        productRepository.save(mockProduct);
        Long id = mockProduct.getId();
        Product product = productRepository.findById(id).orElseThrow();

        assertEquals(id, product.getId());
        assertEquals(mockProduct.getName(), product.getName());
        assertEquals(mockProduct.getSku(), product.getSku());
        assertEquals(mockProduct.getCategory(), product.getCategory());
        assertEquals(mockProduct.getDescription(), product.getDescription());
        assertEquals(mockProduct.getPrice(), product.getPrice());
    }

    @Test
    void shouldInvalidatePriceNotNull() {
        Product product = mockProduct(null);
        product.setPrice(null);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertEquals(1, violations.size());
        for (ConstraintViolation<Product> violation : violations) {
            assertEquals("price column must not be null", violation.getMessage());
        }
    }
}
