package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;

import java.math.BigDecimal;

public interface ItemSpecial {
    BigDecimal calculateSpecial(CartItem cartItem);
}
