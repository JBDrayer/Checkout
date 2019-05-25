package com.audition.checkout;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.cart.CartItem;
import com.audition.checkout.inventory.Inventory;
import com.audition.checkout.inventory.InventoryItem;
import com.audition.checkout.inventory.InventoryManagement;
import com.audition.checkout.utils.BigDecimalFormatter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutE2ETests {
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart(cartItems);
    private List<InventoryItem> inventoryItems = Collections.singletonList(new InventoryItem("soup", new BigDecimal(1.89)));
    private Inventory inventory = new Inventory(inventoryItems);
    private InventoryManagement inventoryManagement = new InventoryManagement(inventory);
    private Checkout checkout = new Checkout(cart, inventoryManagement);

    @Test
    void calculatesTotalForItem() {
        checkout.addItemToCart("soup");

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));
    }

    @Test
    void calculatesTotalForMultipleItems() {
        checkout.addItemToCart("soup");

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addItemToCart("soup");

        total = checkout.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.78)));
    }

    @Test
    void calculatesTotalForWeightedItem() {
        checkout.addWeightedItemToCart("ground beef", BigDecimal.ONE);

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5.99)));
    }
}
