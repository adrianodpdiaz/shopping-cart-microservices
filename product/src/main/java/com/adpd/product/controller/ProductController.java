package com.adpd.product.controller;

import com.adpd.feignclients.resource.dto.ProductDTO;
import com.adpd.product.resource.form.RegisterProductForm;
import com.adpd.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@Tag(name = "Product")
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Operation(
        summary = "Register new product",
        description = "Post endpoint to register a new product.\n" +
            "When providing the required payload information, the product's SKU (Stock Keeping Units) number " +
            "will be generated with the pattern: First two characters from category in uppercase + \"2267T\" " +
            "+ a sequence increasing identifier according to the number of items registered."
    )
    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerProduct(@Valid @RequestBody RegisterProductForm registerProductForm) {
        Long productId = productService.registerProduct(registerProductForm);
        log.info("registered product {}", productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
        summary = "List products",
        description = "Get endpoint to get a list of all products."
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<ProductDTO> products = productService.listProducts();
        log.info("retrieved product list.");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(
        summary = "Get product by id",
        description = "Get endpoint to get a product's information."
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.getProduct(id);
        log.info("retrieved product {}", id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
