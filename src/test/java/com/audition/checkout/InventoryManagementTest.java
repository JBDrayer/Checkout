package com.audition.checkout;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryManagementTest {

    @Mock private InventoryItem inventoryItem;
    @Mock private Inventory inventory;
    @Mock private Cart cart;
    private String itemName = RandomStringUtils.randomAlphanumeric(10);

    @Test
    void addsItemFromInventoryToCart() {
        InventoryManagement inventoryManagement = new InventoryManagement(inventory);
        when(inventory.getItem(itemName)).thenReturn(inventoryItem);

        inventoryManagement.addItemToCart(itemName, cart);

        verify(cart).addItem(inventoryItem);
    }
}