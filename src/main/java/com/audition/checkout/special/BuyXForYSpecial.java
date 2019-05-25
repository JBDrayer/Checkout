package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class BuyXForYSpecial implements ItemSpecial {
    private int quantityNeeded;
    private BigDecimal specialPrice;

    public BuyXForYSpecial(int quantityNeeded, BigDecimal specialPrice, int specialLimit) {
        this.quantityNeeded = quantityNeeded;
        this.specialPrice = specialPrice;
    }

    @Override
    public BigDecimal calculateSpecial(CartItem cartItem) {
        BigDecimal total = BigDecimal.ZERO;
        int quantity = cartItem.getQuantity();
        int numberOfQualifyingSpecials = quantity / quantityNeeded;
        int numberOfItemsRemaining = quantity;
        for (int index = 0; index < numberOfQualifyingSpecials; index++) {
            total = total.add(specialPrice);
            numberOfItemsRemaining -= quantityNeeded;
        }
        total = total.add(cartItem.getPrice().multiply(new BigDecimal(numberOfItemsRemaining)));
        return BigDecimalFormatter.formatForMoney(total);
    }
}
