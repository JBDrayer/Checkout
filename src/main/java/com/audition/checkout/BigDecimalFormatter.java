package com.audition.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalFormatter {
    private static final int PRECISION = 2;
    public static BigDecimal formatForMoney(BigDecimal decimalToFormat) {
        return decimalToFormat.setScale(PRECISION,RoundingMode.HALF_EVEN);
    }
}
