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
class BuyXGetYForZPercentOffSpecialTest {
    @Mock private CartItem cartItem;
    private BigDecimal price = BigDecimal.ONE;
    private BuyXGetYForZPercentOffSpecial buyXGetYForZPercentOffSpecial = new BuyXGetYForZPercentOffSpecial(2,1,1,new BigDecimal(.5));

    @Test
    void calculatesBuyXGetYForZPercentOff() {
        when(cartItem.getPrice()).thenReturn(price);
        when(cartItem.getQuantity()).thenReturn(3);

        BigDecimal total = buyXGetYForZPercentOffSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(2.5)));
    }

    @Test
    void calculatesTotalIfRemainingNumberOfItemsDoesNotMeetSpecial() {
        when(cartItem.getPrice()).thenReturn(price);
        when(cartItem.getQuantity()).thenReturn(4);

        BigDecimal total = buyXGetYForZPercentOffSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.5)));
    }

    @Test
    void calculatesTotalWhenSpecialLimitExceeded() {
        when(cartItem.getPrice()).thenReturn(price);
        when(cartItem.getQuantity()).thenReturn(6);

        BigDecimal total = buyXGetYForZPercentOffSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5.5)));
    }

    @Test
    void calculatesTotalWhenSpecialLimitNotSet() {
        BuyXGetYForZPercentOffSpecial buyXGetYForZPercentOffSpecial = new BuyXGetYForZPercentOffSpecial(2,1,0,new BigDecimal(.5));
        when(cartItem.getPrice()).thenReturn(price);
        when(cartItem.getQuantity()).thenReturn(6);

        BigDecimal total = buyXGetYForZPercentOffSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5)));
    }
}