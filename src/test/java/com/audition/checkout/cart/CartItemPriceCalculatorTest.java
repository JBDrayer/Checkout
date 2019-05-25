package com.audition.checkout.cart;

import com.audition.checkout.inventory.InventoryItem;
import com.audition.checkout.special.ItemSpecial;
import com.audition.checkout.utils.BigDecimalFormatter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartItemPriceCalculatorTest {

    private String itemName = RandomStringUtils.randomAlphanumeric(10);
    private BigDecimal price = new BigDecimal(RandomUtils.nextInt(1,10));
    private InventoryItem inventoryItem = new InventoryItem(itemName, price);
    private CartItem cartItem  = new CartItem(inventoryItem);
    private CartItemPriceCalculator cartItemPriceCalculator = new CartItemPriceCalculator();
    @Mock private ItemSpecial itemSpecial;

    @Test
    void calculatesItemPrice() {
        BigDecimal total = cartItemPriceCalculator.calculateItemPrice(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(price));
    }

    @Test
    void calculatesWeightedItemPrice() {
        BigDecimal weight = new BigDecimal(1.5);
        cartItem.updateWeight(weight);

        BigDecimal total = cartItemPriceCalculator.calculateItemPrice(cartItem);

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(price.multiply(weight)));
    }

    @Test
    void calculatesItemPriceWithItemSpecial() {
        inventoryItem.setItemSpecial(itemSpecial);

        cartItemPriceCalculator.calculateItemPrice(cartItem);

        verify(itemSpecial).calculateSpecial(cartItem);
    }
}