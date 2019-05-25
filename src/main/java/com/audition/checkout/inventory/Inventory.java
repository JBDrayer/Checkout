package com.audition.checkout.inventory;

import com.audition.checkout.ItemSpecial;

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


    public void addSpecialToInventoryItem(ItemSpecial itemSpecial, String itemName) {
        InventoryItem inventoryItem  = getInventoryItem(itemName);
        inventoryItem.setItemSpecial(itemSpecial);
    }

    private InventoryItem getInventoryItem(String itemName) {
        return inventoryItems.stream().filter(inventoryItem ->
                inventoryItem.getName()
                        .equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new InventoryItemNotFoundException(itemName + " not found in inventory"));
    }
}
