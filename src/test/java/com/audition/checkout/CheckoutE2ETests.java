package com.audition.checkout;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutE2ETests {
    private Checkout checkout = new Checkout();
    @Test
    void calculatesTotalForItem() {
        checkout.addItem("soup");

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(new BigDecimal(1.89).setScale(2, RoundingMode.HALF_EVEN));
    }
}
