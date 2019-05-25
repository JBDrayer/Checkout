package com.audition.checkout.inventory;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.inventory.Inventory;
import com.audition.checkout.inventory.InventoryItem;
import com.audition.checkout.inventory.InventoryManagement;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryManagementTest {

    @Mock private InventoryItem inventoryItem;
    @Mock private Inventory inventory;
    @Mock private Cart cart;
    private String itemName = RandomStringUtils.randomAlphanumeric(10);
    private BigDecimal weight = new BigDecimal(RandomUtils.nextInt(1,10));
    private InventoryManagement inventoryManagement;

    @BeforeEach
    void configureInventoryManagement() {
        inventoryManagement = new InventoryManagement(inventory);
    }

    @Test
    void addsItemFromInventoryToCart() {
        when(inventory.getItem(itemName)).thenReturn(inventoryItem);

        inventoryManagement.addItemToCart(itemName, cart);

        verify(cart).addItem(inventoryItem);
    }

    @Test
    void addsWeightedItemFromInventoryToCart() {
        when(inventory.getItem(itemName)).thenReturn(inventoryItem);

        inventoryManagement.addWeightedItemToCart(itemName, weight, cart);

        verify(cart).addWeightedItem(inventoryItem, weight);

    }
}