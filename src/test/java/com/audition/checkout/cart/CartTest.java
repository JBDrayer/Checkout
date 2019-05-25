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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartTest {
    @Mock private CartItemPriceCalculator cartItemPriceCalculator;
    private String itemName = RandomStringUtils.randomAlphanumeric(10);
    private BigDecimal weight = new BigDecimal(RandomUtils.nextInt(1,10));
    private BigDecimal price= new BigDecimal(RandomUtils.nextInt(1,10));
    private InventoryItem inventoryItem = new InventoryItem(itemName, price);
    private CartItem cartItem = new CartItem(inventoryItem);
    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart;

    @BeforeEach
    void configureCart() {
        cart = new Cart(cartItems, cartItemPriceCalculator);
    }

    @Test
    void addItemToCart() {
        cart.addItem(inventoryItem);

        assertThat(cartItems).isNotEmpty();
        assertThat(cartItems.get(0).getName()).isEqualTo(itemName);
    }

    @Test
    void updatesQuantityIfItemAlreadyInCart() {
        cart.addItem(inventoryItem);
        cart.addItem(inventoryItem);

        assertThat(cartItems.size()).isEqualTo(1);
        assertThat(cartItems.get(0).getName()).isEqualTo(itemName);
        assertThat(cartItems.get(0).getQuantity()).isEqualTo(2);

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
        cart.addWeightedItem(inventoryItem, weight);

        assertThat(cartItems).isNotEmpty();
        assertThat(cartItems.get(0).getName()).isEqualTo(itemName);
        assertThat(cartItems.get(0).getWeight()).isEqualTo(weight);
    }

    @Test
    void addsWeightToExistingItemIfWeightedItemAlreadyInCart() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cartItem.updateWeight(weight);
        cart = new Cart(cartItems, cartItemPriceCalculator);

        cart.addWeightedItem(inventoryItem, weight);

        assertThat(cartItems.size()).isEqualTo(1);
        assertThat(cartItems.get(0).getName()).isEqualTo(itemName);
        assertThat(cartItems.get(0).getWeight()).isEqualTo(weight.multiply(new BigDecimal(2)));
    }
}
