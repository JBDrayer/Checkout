package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.special.ItemSpecial;

import java.math.BigDecimal;

public class BuyXWeightGetYForZPercentOff implements ItemSpecial {
    private final BigDecimal weightNeeded;
    private final BigDecimal weightDiscounted;
    private final BigDecimal specialLimit;
    private final BigDecimal percentDiscounted;

    public BuyXWeightGetYForZPercentOff(BigDecimal weightNeeded, BigDecimal weightDiscounted, BigDecimal specialLimit, BigDecimal percentDiscounted) {
        this.weightNeeded = weightNeeded;
        this.weightDiscounted = weightDiscounted;
        this.specialLimit = specialLimit;
        this.percentDiscounted = percentDiscounted;
    }

    @Override
    public BigDecimal calculateSpecial(CartItem cartItem) {
        return null;
    }
}
