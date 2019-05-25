package com.audition.checkout.cart;

import com.audition.checkout.inventory.InventoryItem;

import java.math.BigDecimal;

public class CartItem {
    private InventoryItem inventoryItem;

    public CartItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public String getName() {
        return inventoryItem.getName();
    }

    public BigDecimal getPrice() {
        return inventoryItem.getPrice();
    }
}
