package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.special.ItemSpecial;

import java.math.BigDecimal;

public class BuyXGetYForZSpecial implements ItemSpecial {
    public BuyXGetYForZSpecial(int quantityNeeded, int quantityDiscounted, BigDecimal discountPercentage) {
    }

    @Override
    public BigDecimal calculateSpecial(CartItem cartItem) {
        return null;
    }
}
