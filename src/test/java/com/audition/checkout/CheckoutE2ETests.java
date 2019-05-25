package com.audition.checkout;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutE2ETests {
    private Cart cart = new Cart();
    private List<InventoryItem> inventoryItems = Collections.singletonList(new InventoryItem("soup"));
    private Inventory inventory = new Inventory(inventoryItems);
    private InventoryManagement inventoryManagement = new InventoryManagement(inventory);
    private Checkout checkout = new Checkout(cart, inventoryManagement);
    @Test
    void calculatesTotalForItem() {
        checkout.addItemToCart("soup");

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(new BigDecimal(1.89).setScale(2, RoundingMode.HALF_EVEN));
    }
}
