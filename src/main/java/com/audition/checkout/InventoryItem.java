package com.audition.checkout;

public class InventoryItem {
    private String itemName;

    public InventoryItem(String itemName) {
        this.itemName = itemName;
    }

    public String getName() {
        return itemName;
    }
}
