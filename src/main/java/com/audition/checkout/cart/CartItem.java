package com.audition.checkout.cart;

import com.audition.checkout.inventory.InventoryItem;
import com.audition.checkout.special.ItemSpecial;

import java.math.BigDecimal;

public class CartItem {
    private InventoryItem inventoryItem;
    private BigDecimal weight = BigDecimal.ZERO;
    private int quantity = 1;

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

    void updateWeight(BigDecimal weightToAdd) {
        weight = weight.add(weightToAdd);
    }

    ItemSpecial getItemSpecial() {
        return inventoryItem.getItemSpecial();
    }

    public int getQuantity() {
        return quantity;
    }

    void updateQuantity() {
        quantity++;
    }
}
