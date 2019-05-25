package com.audition.checkout;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addItem(InventoryItem inventoryItem) {
        cartItems.add(new CartItem(inventoryItem));
    }

    public BigDecimal calculateTotal() {
        return null;
    }
}
