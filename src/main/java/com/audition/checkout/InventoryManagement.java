package com.audition.checkout;

public class InventoryManagement {
    private Inventory inventory;

    public InventoryManagement(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItemToCart(String itemName, Cart cart) {
        cart.addItem(inventory.getItem(itemName));
    }
}
