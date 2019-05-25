package com.audition.checkout;

import java.util.List;

public class Inventory {
    private List<InventoryItem> inventoryItems;

    public Inventory(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public InventoryItem getItem(String itemName) {
        return inventoryItems.stream().filter(inventoryItem ->
                inventoryItem.getName()
                .equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new InventoryItemNotFoundException(itemName + "not found in inventory"));
    }
}
