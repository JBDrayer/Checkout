package com.audition.checkout.inventory;

import java.math.BigDecimal;
import java.util.List;

public class Inventory {
    private List<InventoryItem> inventoryItems;

    public Inventory(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public InventoryItem getItem(String itemName) {
        return getInventoryItem(itemName);
    }


    public void markDownItem(String itemName, BigDecimal markdown) {
        InventoryItem inventoryItem = getInventoryItem(itemName);
        inventoryItem.setMarkDown(markdown);
    }

    private InventoryItem getInventoryItem(String itemName) {
        return inventoryItems.stream().filter(inventoryItem ->
                inventoryItem.getName()
                        .equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new InventoryItemNotFoundException(itemName + " not found in inventory"));
    }
}
