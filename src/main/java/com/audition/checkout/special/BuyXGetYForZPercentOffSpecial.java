package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class BuyXGetYForZPercentOffSpecial implements ItemSpecial {
    private final int quantityNeeded;
    private final int quantityDiscounted;
    private int specialLimit;
    private final BigDecimal discountPercentage;

    public BuyXGetYForZPercentOffSpecial(int quantityNeeded, int quantityDiscounted, int specialLimit, BigDecimal discountPercentage) {
        this.quantityNeeded = quantityNeeded;
        this.quantityDiscounted = quantityDiscounted;
        this.specialLimit = specialLimit;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public BigDecimal calculateSpecial(CartItem cartItem) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal price = cartItem.getPrice();
        int quantity = cartItem.getQuantity();
        int specialsUsed = 0;
        for(int index = 0; index < quantity; index ++){
            if(numberOfRequiredItemsMet(quantity) && (numberOfSpecialItemsMet(quantity)) && specialsUsed < specialLimit){
                total = total.add(getTotalOfRegularPriceItems(price));
                total = total.add(getTotalOfSpecialPriceItems(price));
                index = moveIndexToNextNonCalculatedItem(index);
                specialsUsed++;
            }else {
                total = total.add(price);
            }
        }
        return BigDecimalFormatter.formatForMoney(total);
    }

    private boolean numberOfRequiredItemsMet(int quantity) {
        return quantity >= quantityNeeded;
    }

    private boolean numberOfSpecialItemsMet(int quantity) {
        return quantity - quantityNeeded >= quantityDiscounted;
    }

    private BigDecimal getTotalOfRegularPriceItems(BigDecimal itemPrice) {
        return BigDecimalFormatter.formatForMoney(itemPrice.multiply(new BigDecimal(quantityNeeded)));
    }

    private BigDecimal getTotalOfSpecialPriceItems(BigDecimal price) {
        return BigDecimalFormatter.formatForMoney(price.multiply(new BigDecimal(quantityDiscounted)
                .multiply(discountPercentage)));
    }

    private int moveIndexToNextNonCalculatedItem(int index) {
        index += quantityDiscounted + 1;
        return index;
    }
}
