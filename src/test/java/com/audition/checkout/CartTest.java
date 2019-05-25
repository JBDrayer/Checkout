package com.audition.checkout;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartTest {
    @Mock private InventoryItem inventoryItem;
    private List<CartItem> cartItems = new ArrayList<>();
    private String itemName = RandomStringUtils.randomAlphanumeric(10);

    @Test
    void addItemToCart() {
        Cart cart = new Cart(cartItems);
        when(inventoryItem.getName()).thenReturn(itemName);

        cart.addItem(inventoryItem);

        assertThat(cartItems).isNotEmpty();
        assertThat(cartItems.get(0).getName()).isEqualTo(itemName);
    }
}