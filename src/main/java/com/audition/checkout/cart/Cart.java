package com.audition.checkout.cart;

import com.audition.checkout.utils.BigDecimalFormatter;
import com.audition.checkout.inventory.InventoryItem;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addItem(InventoryItem inventoryItem) {
        cartItems.add(new CartItem(inventoryItem));
    }

    public void addWeightedItem(InventoryItem inventoryItem, BigDecimal weight) {
        CartItem cartItem = new CartItem(inventoryItem);
        cartItem.setWeight(weight);
        cartItems.add(cartItem);
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(CartItem cartItem : cartItems){
            total = total.add(cartItem.getPrice());
        }
        return BigDecimalFormatter.formatForMoney(total);
    }
}
