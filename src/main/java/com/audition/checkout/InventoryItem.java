package com.audition.checkout;

import java.math.BigDecimal;

public class InventoryItem {
    private String itemName;
    private BigDecimal price;

    public InventoryItem(String itemName, BigDecimal price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
