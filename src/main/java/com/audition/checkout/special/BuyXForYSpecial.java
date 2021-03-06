package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class BuyXForYSpecial implements ItemSpecial {
    private int quantityNeeded;
    private BigDecimal specialPrice;
    private int specialLimit;

    public BuyXForYSpecial(int quantityNeeded, BigDecimal specialPrice, int specialLimit) {
        this.quantityNeeded = quantityNeeded;
        this.specialPrice = specialPrice;
        this.specialLimit = specialLimit;
    }

    @Override
    public BigDecimal calculateSpecial(CartItem cartItem) {
        BigDecimal total = BigDecimal.ZERO;
        int quantity = cartItem.getQuantity();
        int numberOfQualifyingSpecials = quantity / quantityNeeded;
        int specialsUsed = 0;
        for (int index = 0; index < numberOfQualifyingSpecials; index++) {
            if((specialLimit == 0) || (specialsUsed < specialLimit)) {
                total = total.add(specialPrice);
                quantity -= quantityNeeded;
                specialsUsed++;
            }
        }
        total = total.add(cartItem.getPrice().multiply(new BigDecimal(quantity)));
        return BigDecimalFormatter.formatForMoney(total);
    }
}
