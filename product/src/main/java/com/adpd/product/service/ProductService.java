package com.adpd.product.service;

import com.adpd.product.entity.Product;
import com.adpd.product.mapping.ProductMapper;
import com.adpd.product.repository.ProductRepository;
import com.adpd.product.resource.outbound.ProductDTO;
import com.adpd.product.resource.inbound.RegisterProductInbound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer registerProduct(RegisterProductInbound registerProductInbound) {
        Integer maxId = productRepository.findMaxIdByCategory(registerProductInbound.getCategory())
                .map(Product::getId).orElse(0);
        String sku = registerProductInbound.getCategory().substring(0, 2).toUpperCase() +
                "2267T" + ((maxId == 0) ? 1 : maxId);

        Product product = productMapper.requestToEntity(registerProductInbound);
        product.setSku(sku);
        productRepository.saveAndFlush(product);

        return product.getId();
    }

    public ProductDTO getProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Register not found for product with id: %d", id)));
        return productMapper.toDTO(product);
    }
}
