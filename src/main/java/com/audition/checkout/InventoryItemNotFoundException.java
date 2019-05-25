package com.audition.checkout;

public class InventoryItemNotFoundException extends RuntimeException{
    public InventoryItemNotFoundException(String message) {
        super(message);
    }
}
