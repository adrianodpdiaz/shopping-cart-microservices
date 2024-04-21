package com.adpd.product.controller;

import com.adpd.product.resource.outbound.ProductDTO;
import com.adpd.product.resource.inbound.RegisterProductInbound;
import com.adpd.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @PostMapping
    public ResponseEntity<Void> registerProduct(@Valid @RequestBody RegisterProductInbound registerProductInbound) {
        Integer productId = productService.registerProduct(registerProductInbound);
        log.info("registered product {}", productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Get product by id",
            description = "Get endpoint to get a product's information."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Integer id) {
        ProductDTO productDTO = productService.getProduct(id);
        log.info("retrieved product {}", id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
