package com.audition.checkout.cart;

import com.audition.checkout.inventory.InventoryItem;
import com.audition.checkout.utils.BigDecimalFormatter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartTest {
    @Mock private CartItemPriceCalculator cartItemPriceCalculator;
    @Mock private InventoryItem inventoryItem;
    @Mock private CartItem cartItem;
    private List<CartItem> cartItems = new ArrayList<>();
    private String itemName = RandomStringUtils.randomAlphanumeric(10);
    private BigDecimal weight = new BigDecimal(RandomUtils.nextInt(1,10));
    private Cart cart;

    @BeforeEach
    void configureCart() {
        cart = new Cart(cartItems, cartItemPriceCalculator);
    }

    @Test
    void addItemToCart() {
        when(inventoryItem.getName()).thenReturn(itemName);

        cart.addItem(inventoryItem);

        assertThat(cartItems).isNotEmpty();
        assertThat(cartItems.get(0).getName()).isEqualTo(itemName);
    }

    @Test
    void calculatesTotalForItemsInCart() {
        cart = new Cart(Collections.singletonList(cartItem), cartItemPriceCalculator);
        BigDecimal total = new BigDecimal(RandomUtils.nextInt(1,10));
        when(cartItemPriceCalculator.calculateItemPrice(cartItem)).thenReturn(total);

        BigDecimal results = cart.calculateTotal();

        assertThat(results).isEqualTo(BigDecimalFormatter.formatForMoney(total));
    }

    @Test
    void addWeightedItemToCart() {
        when(inventoryItem.getName()).thenReturn(itemName);

        cart.addWeightedItem(inventoryItem, weight);

        assertThat(cartItems).isNotEmpty();
        assertThat(cartItems.get(0).getName()).isEqualTo(itemName);
        assertThat(cartItems.get(0).getWeight()).isEqualTo(weight);
    }
}