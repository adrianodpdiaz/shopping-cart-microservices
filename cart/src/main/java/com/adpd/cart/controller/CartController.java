package com.adpd.cart.controller;

import com.adpd.cart.resource.form.CartItemForm;
import com.adpd.cart.resource.form.CreateCartForm;
import com.adpd.cart.resource.dto.CartDTO;
import com.adpd.cart.service.CartService;
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

@Slf4j
@Validated
@RestController
@Tag(name = "Cart")
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @Operation(
        summary = "Create new cart",
        description = "Post endpoint to create a new cart."
    )
    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@Valid @RequestBody CreateCartForm createCartForm) {
        Long cartId = cartService.createCart(createCartForm);
        log.info("registered product {}", cartId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get cart by id",
        description = "Get endpoint to get the cart's information."
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> getCart(@PathVariable("id") Long id) {
        CartDTO cartDTO = cartService.getCart(id);
        log.info("retrieved cart {}", id);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @Operation(
        summary = "Add item to cart",
        description = "Post endpoint to add an item to a cart."
    )
    @Transactional
    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItem(@PathVariable("id") Long cartId,
            @Valid @RequestBody CartItemForm cartItemForm) {
        cartService.addItem(cartId, cartItemForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
        summary = "Remove item from the cart",
        description = "Delete endpoint to remove an item from the cart."
    )
    @Transactional
    @DeleteMapping(value = "/{id}/remove")
    public ResponseEntity<Void> removeItem(@PathVariable("id") Long cartId,
            @RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        cartService.removeItem(cartId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
