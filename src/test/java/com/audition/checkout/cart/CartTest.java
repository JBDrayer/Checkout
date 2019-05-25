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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartTest {
    @Mock private InventoryItem inventoryItem;
    @Mock private CartItem cartItem;
    private List<CartItem> cartItems = new ArrayList<>();
    private String itemName = RandomStringUtils.randomAlphanumeric(10);
    private BigDecimal weight = new BigDecimal(RandomUtils.nextInt(1,10));
    private Cart cart;

    @BeforeEach
    void configureCart() {
        cart = new Cart(cartItems);
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
        cart = new Cart(Arrays.asList(cartItem, cartItem));
        when(cartItem.getPrice()).thenReturn(BigDecimal.ONE);

        BigDecimal total = cart.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(2)));
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