package com.audition.checkout.cart;

import com.audition.checkout.inventory.InventoryItem;

import java.math.BigDecimal;

public class CartItem {
    private InventoryItem inventoryItem;
    private BigDecimal weight;

    public CartItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public String getName() {
        return inventoryItem.getName();
    }

    public BigDecimal getPrice() {
        return inventoryItem.getPrice();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
