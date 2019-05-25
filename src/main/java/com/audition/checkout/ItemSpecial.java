package com.audition.checkout;

import com.audition.checkout.cart.CartItem;

import java.math.BigDecimal;

public interface ItemSpecial {
    BigDecimal calculateSpecial(CartItem cartItem);
}
