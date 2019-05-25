package com.audition.checkout;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.inventory.InventoryManagement;
import com.audition.checkout.special.ItemSpecial;

import java.math.BigDecimal;

public class Checkout {
    private Cart cart;
    private InventoryManagement inventoryManagement;

    public Checkout(Cart cart, InventoryManagement inventoryManagement) {
        this.cart = cart;
        this.inventoryManagement = inventoryManagement;
    }

    public void addItemToCart(String itemName) {
        inventoryManagement.addItemToCart(itemName, cart);
    }

    public void removeItemFromCart(String itemName) {
        cart.removeItem(itemName);
    }

    public void addWeightedItemToCart(String itemName, BigDecimal weight) {
        inventoryManagement.addWeightedItemToCart(itemName, weight, cart);
    }

    public void markDownItem(String itemName, BigDecimal markDown) {
        inventoryManagement.markDownItem(itemName, markDown);
    }

    public void addSpecialToInventoryItem(ItemSpecial itemSpecial, String itemName) {
        inventoryManagement.addSpecialToInventoryItem(itemSpecial, itemName);
    }

    public BigDecimal calculateTotal() {
        return cart.calculateTotal();
    }
}
