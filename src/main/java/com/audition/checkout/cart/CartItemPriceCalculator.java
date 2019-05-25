package com.audition.checkout.cart;

import java.math.BigDecimal;

public class CartItemPriceCalculator {
    public BigDecimal calculateItemPrice(CartItem cartItem) {
        return cartItem.getWeight().compareTo(BigDecimal.ZERO) > 0 ?
        cartItem.getPrice().multiply(cartItem.getWeight()) :
        cartItem.getPrice();
    }
}
