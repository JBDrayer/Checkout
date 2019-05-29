package com.audition.checkout.inventory;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.special.ItemSpecial;

import java.math.BigDecimal;

public class InventoryManagement {
    private Inventory inventory;

    public InventoryManagement(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItemToCart(String itemName, Cart cart) {
        cart.addItem(inventory.getItem(itemName));
    }

    public void addWeightedItemToCart(String itemName, BigDecimal weight, Cart cart) {
        cart.addWeightedItem(inventory.getItem(itemName), weight);
    }

    public void markDownItem(String itemName, BigDecimal markDown) {
        inventory.markDownItem(itemName, markDown);
    }

    public void addSpecialToInventoryItem(ItemSpecial itemspecial, String itemName) {
        inventory.addSpecialToInventoryItem(itemspecial, itemName);
    }

    public void adjustDefaultItemPrice(String itemName, BigDecimal itemPrice) {
        inventory.adjustDefaultItemPrice(itemName, itemPrice);
    }
}
