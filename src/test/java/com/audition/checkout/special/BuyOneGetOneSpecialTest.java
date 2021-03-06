package com.audition.checkout.special;

import com.audition.checkout.cart.CartItem;
import com.audition.checkout.utils.BigDecimalFormatter;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyOneGetOneSpecialTest {

    @Mock private CartItem cartItem;
    private BigDecimal price = new BigDecimal(RandomUtils.nextInt(1,10));
    private int specialLimit = 1;

    @Test
    void calculatesBuyOneGetOneSpecial() {
        BuyOneGetOneSpecial buyOneGetOneSpecial = new BuyOneGetOneSpecial(specialLimit);
        int quantity = 2;
        when(cartItem.getQuantity()).thenReturn(quantity);
        when(cartItem.getPrice()).thenReturn(price);

        BigDecimal total = buyOneGetOneSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(price));
    }

    @Test
    void calculatesTotalForRemainingItems() {
        BuyOneGetOneSpecial buyOneGetOneSpecial = new BuyOneGetOneSpecial(specialLimit);
        int quantity = 3;
        when(cartItem.getQuantity()).thenReturn(quantity);
        when(cartItem.getPrice()).thenReturn(price);

        BigDecimal total = buyOneGetOneSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(price.multiply(new BigDecimal(2))));
    }

    @Test
    void calculateTotalWhenSpecialLimitExceeded() {
        BuyOneGetOneSpecial buyOneGetOneSpecial = new BuyOneGetOneSpecial(specialLimit);
        int quantity = 4;
        when(cartItem.getQuantity()).thenReturn(quantity);
        when(cartItem.getPrice()).thenReturn(price);

        BigDecimal total = buyOneGetOneSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(price.multiply(new BigDecimal(3))));
    }

    @Test
    void calculatesSpecialWhenNoLimitIsSet() {
        specialLimit = 0;
        BuyOneGetOneSpecial buyOneGetOneSpecial = new BuyOneGetOneSpecial(specialLimit);
        int quantity = 6;
        when(cartItem.getQuantity()).thenReturn(quantity);
        when(cartItem.getPrice()).thenReturn(price);

        BigDecimal total = buyOneGetOneSpecial.calculateSpecial(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(price.multiply(new BigDecimal(3))));
    }
}