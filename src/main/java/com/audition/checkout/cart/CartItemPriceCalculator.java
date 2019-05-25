package com.audition.checkout.cart;

import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class CartItemPriceCalculator {
    public BigDecimal calculateItemPrice(CartItem cartItem) {
        return cartItem.getWeight().compareTo(BigDecimal.ZERO) > 0 ?
            BigDecimalFormatter.formatForMoney(cartItem.getPrice().multiply(cartItem.getWeight())) :
            BigDecimalFormatter.formatForMoney(cartItem.getPrice());
    }
}
