package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;

public class BuyXGetYForZPercentOffSpecial implements ItemSpecial {
    private final int quantityNeeded;
    private final int quantityDiscounted;
    private final BigDecimal discountPercentage;

    public BuyXGetYForZPercentOffSpecial(int quantityNeeded, int quantityDiscounted, BigDecimal discountPercentage) {
        this.quantityNeeded = quantityNeeded;
        this.quantityDiscounted = quantityDiscounted;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public BigDecimal calculateSpecial(CartItem cartItem) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal price = cartItem.getPrice();
        int quantity = cartItem.getQuantity();
        for(int index = 0; index < quantity; index ++){
            if(numberOfRequiredItemsMet(index) && (numberOfSpecialItemsMet(quantity))){
                total = total.add(getTotalOfRegularPriceItems(price));
                total = total.add(getTotalOfSpecialPriceItems(price));
                index = moveIndexToNextNonCalculatedItem(index);
            }else {
                total = total.add(price);
            }
        }
        return BigDecimalFormatter.formatForMoney(total);
    }

    private boolean numberOfRequiredItemsMet(int index) {
        return index < quantityNeeded;
    }

    private boolean numberOfSpecialItemsMet(int quantity) {
        return quantity - quantityNeeded >= quantityDiscounted;
    }

    private BigDecimal getTotalOfRegularPriceItems(BigDecimal itemPrice) {
        return itemPrice.multiply(new BigDecimal(quantityNeeded));
    }

    private BigDecimal getTotalOfSpecialPriceItems(BigDecimal price) {
        return price.multiply(new BigDecimal(quantityDiscounted)
                .multiply(discountPercentage));
    }

    private int moveIndexToNextNonCalculatedItem(int index) {
        index += quantityDiscounted + 1;
        return index;
    }
}
