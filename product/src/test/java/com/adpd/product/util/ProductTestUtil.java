package com.adpd.product.util;

import com.adpd.product.entity.Product;
import com.adpd.product.resource.dto.ProductDTO;
import com.adpd.product.resource.form.RegisterProductForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductTestUtil {

    private static final String PRODUCT_NAME = "Product";
    private static final String PRODUCT_CATEGORY = "Category";
    private static final String PRODUCT_DESCRIPTION = "Description";
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal("9.99");
    public static final String PRODUCT_SKU = "CA2267T1";

    public static RegisterProductForm mockRegisterProductForm() {
        RegisterProductForm registerProductForm = new RegisterProductForm();
        registerProductForm.setName(PRODUCT_NAME);
        registerProductForm.setCategory(PRODUCT_CATEGORY);
        registerProductForm.setDescription(PRODUCT_DESCRIPTION);
        registerProductForm.setPrice(PRODUCT_PRICE);
        return registerProductForm;
    }

    public static Product mockProduct(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName(PRODUCT_NAME);
        product.setCategory(PRODUCT_CATEGORY);
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setPrice(PRODUCT_PRICE);
        return product;
    }

    public static ProductDTO mockProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(PRODUCT_NAME);
        productDTO.setCategory(PRODUCT_CATEGORY);
        productDTO.setDescription(PRODUCT_DESCRIPTION);
        productDTO.setPrice(PRODUCT_PRICE);
        productDTO.setSku(PRODUCT_SKU);
        return productDTO;
    }
}
