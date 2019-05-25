package com.audition.checkout;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.inventory.InventoryManagement;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
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
    private Checkout checkout;

    @BeforeEach
    void configureCheckout() {
        checkout = new Checkout(cart, inventoryManagement);
    }

    @Test
    void addsItemToCart() {
        checkout.addItemToCart(itemName);

        verify(inventoryManagement).addItemToCart(itemName, cart);
    }

    @Test
    void calculateTotalForCart() {
        checkout.calculateTotal();

        verify(cart).calculateTotal();
    }
}