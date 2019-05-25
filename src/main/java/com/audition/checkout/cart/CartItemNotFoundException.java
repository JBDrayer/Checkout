package com.audition.checkout.cart;

public class CartItemNotFoundException extends  RuntimeException{
    CartItemNotFoundException(String message) {
        super(message);
    }
}
