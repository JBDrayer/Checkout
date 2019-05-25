package com.audition.checkout.inventory;

import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class InventoryItem {
    private String itemName;
    private BigDecimal price;
    private BigDecimal markDown = BigDecimal.ZERO;

    public InventoryItem(String itemName, BigDecimal price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return BigDecimalFormatter.formatForMoney(price.subtract(markDown));
    }

    public void setMarkDown(BigDecimal markDown) {
        this.markDown = markDown;
    }
}
