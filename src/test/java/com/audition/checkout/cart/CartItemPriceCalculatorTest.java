package com.audition.checkout.cart;

import com.audition.checkout.inventory.InventoryItem;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CartItemPriceCalculatorTest {

    private String itemName = RandomStringUtils.randomAlphanumeric(10);
    private BigDecimal price = new BigDecimal(RandomUtils.nextInt(1,10));
    private InventoryItem inventoryItem = new InventoryItem(itemName, price);
    private CartItem cartItem  = new CartItem(inventoryItem);

    @Test
    void calculatesItemPrice() {
        CartItemPriceCalculator cartItemPriceCalculator = new CartItemPriceCalculator();

        BigDecimal total = cartItemPriceCalculator.calculateItemPrice(cartItem);

        assertThat(total).isEqualTo(price);
    }
}