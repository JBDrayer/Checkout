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
class BuyXForYSpecialTest {
    @Mock private CartItem cartItem;
    private BuyXForYSpecial buyXForYSpecial = new BuyXForYSpecial(3, new BigDecimal(5.00), 1);

    @Test
    void calculatesBuyXForYSpecial() {
        when(cartItem.getQuantity()).thenReturn(3);
        when(cartItem.getPrice()).thenReturn(new BigDecimal(2));

        BigDecimal total = buyXForYSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5)));
    }

    @Test
    void calculatesRemainingItemsAfterSpecial() {
        when(cartItem.getQuantity()).thenReturn(5);
        when(cartItem.getPrice()).thenReturn(new BigDecimal(2));

        BigDecimal total = buyXForYSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(9)));
    }

    @Test
    void calculatesTotalWhenSpecialLimitExceeded() {
        when(cartItem.getQuantity()).thenReturn(6);
        when(cartItem.getPrice()).thenReturn(new BigDecimal(2));

        BigDecimal total = buyXForYSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(11)));
    }

    @Test
    void calculatesTotalWhenNoSpecialLimitIsSet() {
        BuyXForYSpecial buyXForYSpecial = new BuyXForYSpecial(3, new BigDecimal(5.00), 0);
        when(cartItem.getQuantity()).thenReturn(6);
        when(cartItem.getPrice()).thenReturn(new BigDecimal(2));

        BigDecimal total = buyXForYSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(10)));
    }
}