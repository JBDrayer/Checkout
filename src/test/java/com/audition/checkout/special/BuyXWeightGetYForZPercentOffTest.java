package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyXWeightGetYForZPercentOffTest {

    @Mock private CartItem cartItem;
    private BuyXWeightGetYForZPercentOff buyXWeightGetYForZPercentOff = new BuyXWeightGetYForZPercentOff(BigDecimal.ONE, BigDecimal.ONE, new BigDecimal(2), new BigDecimal(.5));

    @Test
    void calculatesBuyXWeightGetYForZPercentOff() {
        when(cartItem.getPrice()).thenReturn(BigDecimal.ONE);
        when(cartItem.getWeight()).thenReturn(new BigDecimal(2));

        BigDecimal total = buyXWeightGetYForZPercentOff.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.5)));
    }

    @Test
    void calculatesRemainingWeightWhenSpecialLimitIsReached() {
        when(cartItem.getPrice()).thenReturn(BigDecimal.ONE);
        when(cartItem.getWeight()).thenReturn(new BigDecimal(4));

        BigDecimal total = buyXWeightGetYForZPercentOff.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.5)));
    }
}