package com.adpd.product.service;

import com.adpd.product.entity.Product;
import com.adpd.product.mapping.ProductMapper;
import com.adpd.product.repository.ProductRepository;
import com.adpd.feignclients.resource.dto.ProductDTO;
import com.adpd.product.resource.form.RegisterProductForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Long registerProduct(RegisterProductForm registerProductForm) {
        long maxId = productRepository.findMaxIdByCategory(registerProductForm.getCategory())
            .orElse(0L);
        String sku = registerProductForm.getCategory().substring(0, 2).toUpperCase() +
            "2267T" + ((maxId == 0) ? 1 : maxId + 1);

        Product product = productMapper.requestToEntity(registerProductForm);
        product.setSku(sku);
        productRepository.saveAndFlush(product);

        return product.getId();
    }

    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDTOList(products);
    }

    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Register not found for product with id: %d", id)));
        return productMapper.toDTO(product);
    }
}
