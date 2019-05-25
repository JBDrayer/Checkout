package com.audition.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartItemTest {
    @Mock private InventoryItem inventoryItem;
    private CartItem cartItem;

    @BeforeEach
    void configureCartItem() {
        cartItem = new CartItem(inventoryItem);
    }

    @Test
    void getsItemNameFromInventoryItem() {
        cartItem.getName();

        verify(inventoryItem).getName();
    }

    @Test
    void getsItemPriceFromInventoryItem() {
        cartItem.getPrice();

        verify(inventoryItem).getPrice();
    }
}