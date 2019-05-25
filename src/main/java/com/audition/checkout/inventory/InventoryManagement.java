package com.audition.checkout.inventory;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.inventory.Inventory;

public class InventoryManagement {
    private Inventory inventory;

    public InventoryManagement(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItemToCart(String itemName, Cart cart) {
        cart.addItem(inventory.getItem(itemName));
    }
}
