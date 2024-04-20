package com.adpd.product.controller;

import com.adpd.product.resource.ProductDTO;
import com.adpd.product.resource.RegisterProductRequest;
import com.adpd.product.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> registerProduct(@Valid @RequestBody RegisterProductRequest registerProductRequest) {
        Integer productId = productService.registerProduct(registerProductRequest);
        log.info("registered product {}", productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Integer id) {
        ProductDTO productDTO = productService.getProduct(id);
        log.info("retrieved product {}", id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
