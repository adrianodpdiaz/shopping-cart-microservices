package com.adpd.cart.util;

import com.adpd.cart.entity.Cart;
import com.adpd.cart.entity.Item;
import com.adpd.cart.resource.dto.CartDTO;
import com.adpd.cart.resource.dto.ItemDTO;
import com.adpd.cart.resource.form.CartItemForm;
import com.adpd.cart.resource.form.CreateCartForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartTestUtil {

    private static final Long CUSTOMER_ID = 1L;
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();

    public static CreateCartForm mockCreateCartForm() {
        CreateCartForm createCartForm = new CreateCartForm();
        createCartForm.setCustomerId(CUSTOMER_ID);
        createCartForm.setItems(mockCartItemForms());
        return createCartForm;
    }

    public static Cart mockCart(Long id) {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setCustomerId(CUSTOMER_ID);
        cart.setCreatedAt(CREATED_AT);
        cart.setItems(mockCartItems());
        return cart;
    }

    public static CartDTO mockCartDTO() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerId(CUSTOMER_ID);
        cartDTO.setCreatedAt(LocalDateTime.parse(CREATED_AT.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        cartDTO.setItems(mockCartItemDTOs());
        return cartDTO;
    }

    private static List<CartItemForm> mockCartItemForms() {
        CartItemForm cartItemForm1 = new CartItemForm();
        cartItemForm1.setProductId(1L);
        cartItemForm1.setQuantity(10);
        cartItemForm1.setUnitPrice(new BigDecimal("9.99"));

        CartItemForm cartItemForm2 = new CartItemForm();
        cartItemForm2.setProductId(2L);
        cartItemForm2.setQuantity(2);
        cartItemForm2.setUnitPrice(new BigDecimal("3.59"));

        return List.of(cartItemForm1, cartItemForm2);
    }

    private static Set<Item> mockCartItems() {
        Item item1 = new Item();
        item1.setId(1L);
        item1.setProductId(1L);
        item1.setQuantity(10);
        item1.setUnitPrice(new BigDecimal("9.99"));

        Item item2 = new Item();
        item2.setId(2L);
        item2.setProductId(2L);
        item2.setQuantity(2);
        item2.setUnitPrice(new BigDecimal("3.59"));

        return Set.of(item1, item2);
    }

    private static List<ItemDTO> mockCartItemDTOs() {
        ItemDTO item1 = new ItemDTO();
        item1.setProductId(1L);
        item1.setQuantity(10);
        item1.setUnitPrice(new BigDecimal("9.99"));

        ItemDTO item2 = new ItemDTO();
        item2.setProductId(2L);
        item2.setQuantity(2);
        item2.setUnitPrice(new BigDecimal("3.59"));

        return List.of(item1, item2);
    }
}
