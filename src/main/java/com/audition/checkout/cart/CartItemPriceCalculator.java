package com.audition.checkout.cart;

import java.math.BigDecimal;

public class CartItemPriceCalculator {
    public BigDecimal calculateItemPrice(CartItem cartItem) {
        return cartItem.getPrice();
    }
}
