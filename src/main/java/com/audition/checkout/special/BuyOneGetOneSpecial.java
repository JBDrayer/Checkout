package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class BuyOneGetOneSpecial implements ItemSpecial {
    private int specialLimit;

    public BuyOneGetOneSpecial(int specialLimit) {
        this.specialLimit = specialLimit;
    }

    @Override
    public BigDecimal calculateSpecial(CartItem cartItem) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal price = cartItem.getPrice();
        for(int index = 0; index < cartItem.getQuantity(); index++){
                total = total.add(price);
                index++;
        }
        return BigDecimalFormatter.formatForMoney(total);
    }
}
