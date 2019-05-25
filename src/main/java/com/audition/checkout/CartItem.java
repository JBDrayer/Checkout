package com.audition.checkout;

public class CartItem {

    private InventoryItem inventoryItem;

    public CartItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public String getName() {
        return inventoryItem.getName();
    }
}
