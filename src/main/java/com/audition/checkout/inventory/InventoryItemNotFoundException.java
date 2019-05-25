package com.audition.checkout.inventory;

public class InventoryItemNotFoundException extends RuntimeException{
    InventoryItemNotFoundException(String message) {
        super(message);
    }
}
