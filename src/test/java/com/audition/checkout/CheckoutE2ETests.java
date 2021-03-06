package com.audition.checkout;

import com.audition.checkout.cart.Cart;
import com.audition.checkout.cart.CartItem;
import com.audition.checkout.cart.CartItemPriceCalculator;
import com.audition.checkout.inventory.Inventory;
import com.audition.checkout.inventory.InventoryItem;
import com.audition.checkout.inventory.InventoryManagement;
import com.audition.checkout.special.BuyOneGetOneSpecial;
import com.audition.checkout.special.BuyXForYSpecial;
import com.audition.checkout.special.BuyXGetYForZPercentOffSpecial;
import com.audition.checkout.special.BuyXWeightGetYForZPercentOff;
import com.audition.checkout.utils.BigDecimalFormatter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutE2ETests {
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private CartItemPriceCalculator cartItemPriceCalculator = new CartItemPriceCalculator();
    private Cart cart = new Cart(cartItems, cartItemPriceCalculator);
    private List<InventoryItem> inventoryItems = Arrays.asList(new InventoryItem("soup", new BigDecimal(1.89)),
                                                new InventoryItem(("ground beef"), new BigDecimal(5.99)));
    private Inventory inventory = new Inventory(inventoryItems);
    private InventoryManagement inventoryManagement = new InventoryManagement(inventory);
    private Checkout checkout = new Checkout(cart, inventoryManagement);


    @Test
    void canAdjustPriceOfItemInInventory() {
        assertThat(inventory.getItem("soup").getPrice()).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.adjustDefaultItemPrice("soup",new BigDecimal(1.59));

        assertThat(inventory.getItem("soup").getPrice()).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.59)));
    }

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
        checkout.addWeightedItemToCart("ground beef", new BigDecimal(1.5));

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(8.98)));
    }

    @Test
    void calculatesTotalForMultipleWeightedItems() {
        checkout.addWeightedItemToCart("ground beef", new BigDecimal(1.5));
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(8.98)));

        checkout.addWeightedItemToCart("ground beef", new BigDecimal(1.5));
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(17.97)));
    }

    @Test
    void calculatesTotalForWeightedAndNonWeightedItem() {
        checkout.addItemToCart("soup");
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addWeightedItemToCart("ground beef", new BigDecimal(1.5));
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(10.87)));
    }

    @Test
    void calculatesTotalForItemWithMarkdown() {
        checkout.markDownItem("soup", new BigDecimal(.20));
        checkout.addItemToCart("soup");

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.69)));
    }

    @Test
    void calculatesTotalForBuyOneGetOneSpecial() {
        checkout.addSpecialToInventoryItem(new BuyOneGetOneSpecial(1), "soup");
        checkout.addItemToCart("soup");
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.78)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5.67)));
    }

    @Test
    void calculatesTotalForBuyXGetYForZPercentOffSpecial() {
        checkout.addSpecialToInventoryItem(new BuyXGetYForZPercentOffSpecial(2,1,1,new BigDecimal(.5)), "soup");
        checkout.addItemToCart("soup");
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.78)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(4.72)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(6.61)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(8.50)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(10.39)));
    }

    @Test
    void calculatesTotalForBuyXForYSpecial() {
        checkout.addSpecialToInventoryItem(new BuyXForYSpecial(3,new BigDecimal(5), 1), "soup");
        checkout.addItemToCart("soup");
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.78)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5.00)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(6.89)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(8.78)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(10.67)));
    }

    @Test
    void calculatesTotalForBuyXWeightGetYForZPercentOff() {
        checkout.addSpecialToInventoryItem(new BuyXWeightGetYForZPercentOff(BigDecimal.ONE, BigDecimal.ONE, new BigDecimal(2), new BigDecimal(.5)), "ground beef");
        checkout.addWeightedItemToCart("ground beef", new BigDecimal(3));

        BigDecimal total = checkout.calculateTotal();

        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(14.98)));
    }

    @Test
    void removingAnItemFromCartRemovesItemPriceFromTotal() {
        checkout.addItemToCart("soup");
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.78)));

        checkout.removeItemFromCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));
    }

    @Test
    void removingAWeightedItemFromCartRemovesItemWeightedPriceFromTotal() {
        checkout.addWeightedItemToCart("ground beef", new BigDecimal(1.5));
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(8.98)));

        checkout.removeWeightedItemFromCart("ground beef", new BigDecimal(.5));
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5.99)));
    }

    @Test
    void calculatesTotalForRemovedItemThatInvalidatesSpecial() {
        checkout.addSpecialToInventoryItem(new BuyXForYSpecial(3,new BigDecimal(5), 1), "soup");
        checkout.addItemToCart("soup");
        BigDecimal total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(1.89)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.78)));

        checkout.addItemToCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(5.00)));

        checkout.removeItemFromCart("soup");
        total = checkout.calculateTotal();
        assertThat(total).isEqualTo(BigDecimalFormatter.formatForMoney(new BigDecimal(3.78)));
    }
}
