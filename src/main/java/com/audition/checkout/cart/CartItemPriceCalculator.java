package com.audition.checkout.cart;

import com.audition.checkout.ItemSpecial;
import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class CartItemPriceCalculator {
    public BigDecimal calculateItemPrice(CartItem cartItem) {
        ItemSpecial itemSpecial = cartItem.getItemSpecial();
        if(itemSpecial != null){
           return itemSpecial.calculateSpecial(cartItem);
        } else {
            return cartItem.getWeight().compareTo(BigDecimal.ZERO) > 0 ?
                    BigDecimalFormatter.formatForMoney(cartItem.getPrice()
                            .multiply(new BigDecimal(cartItem.getQuantity()))
                            .multiply(cartItem.getWeight())) :
                    BigDecimalFormatter.formatForMoney(cartItem.getPrice())
                            .multiply(new BigDecimal(cartItem.getQuantity()));
        }
    }
}
