package com.audition.checkout;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CheckoutTest {

    @Mock private Cart cart;
    @Mock private InventoryManagement inventoryManagement;
    private String itemName = RandomStringUtils.randomAlphanumeric(10);

    @Test
    void addsItemToCart() {
        Checkout checkout = new Checkout(cart, inventoryManagement);

        checkout.addItemToCart(itemName);

        verify(inventoryManagement).addItemToCart(itemName, cart);
    }
}