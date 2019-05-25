package com.audition.checkout;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.inventory.InventoryManagement;

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

    public BigDecimal calculateTotal() {
        return cart.calculateTotal();
    }
}
