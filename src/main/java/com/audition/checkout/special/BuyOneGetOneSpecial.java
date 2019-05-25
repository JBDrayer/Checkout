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
        int quantity = cartItem.getQuantity();
        int specialsUsed = 0;
        for(int index = 0; index < quantity; index++){
            if (specialsUsed < specialLimit){
                total = total.add(price);
                specialsUsed++;
                index++;
            } else{
                total = total.add(price);
            }
        }
        return BigDecimalFormatter.formatForMoney(total);
    }
}
