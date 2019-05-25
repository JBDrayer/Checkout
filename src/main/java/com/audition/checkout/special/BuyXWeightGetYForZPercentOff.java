package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;

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
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal price = cartItem.getPrice();
        BigDecimal remainingItemWeight = cartItem.getWeight();
        BigDecimal specialWeightUsed = BigDecimal.ZERO;
        int numberOfQualifyingSpecials = remainingItemWeight
                .divide(weightNeeded, BigDecimal.ROUND_DOWN)
                .intValue();
        for (int i = 0; i < numberOfQualifyingSpecials; i++) {
            if (remainingItemWeight.compareTo(weightNeeded) > 0 && (specialWeightUsed.compareTo(specialLimit) < 0)) {
                total = total.add(price.multiply(weightNeeded));
                total = total.add(price.multiply(weightDiscounted.multiply(percentDiscounted)));
                remainingItemWeight = remainingItemWeight.subtract(weightNeeded);
                remainingItemWeight = remainingItemWeight.subtract(weightDiscounted);
                specialWeightUsed = specialWeightUsed.add(weightDiscounted).add(weightNeeded);
            }
        }
        total = total.add(price.multiply(remainingItemWeight));
        return BigDecimalFormatter.formatForMoney(total);
    }
}
