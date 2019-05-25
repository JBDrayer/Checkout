package com.audition.checkout.cart;

import com.audition.checkout.inventory.InventoryItem;
import com.audition.checkout.utils.BigDecimalFormatter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> cartItems;
    private CartItemPriceCalculator cartItemPriceCalculator;

    public Cart(List<CartItem> cartItems, CartItemPriceCalculator cartItemPriceCalculator) {
        this.cartItems = cartItems;
        this.cartItemPriceCalculator = cartItemPriceCalculator;
    }

    public void addItem(InventoryItem inventoryItem) {
        Optional<CartItem> optionalCartItem = getItemFromCart(inventoryItem);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = cartItems.get(cartItems.indexOf(optionalCartItem.get()));
            cartItem.updateQuantity();
        } else{
            cartItems.add(new CartItem(inventoryItem));
        }
    }

    public void addWeightedItem(InventoryItem inventoryItem, BigDecimal weight) {
        Optional<CartItem> optionalCartItem  = getItemFromCart(inventoryItem);
        if(optionalCartItem.isPresent()){
            CartItem cartItem = optionalCartItem.get();
            cartItem.updateWeight(weight);
        }else {
            CartItem cartItem = new CartItem(inventoryItem);
            cartItem.updateWeight(weight);
            cartItems.add(cartItem);
        }
    }

    private Optional<CartItem> getItemFromCart(InventoryItem inventoryItem) {
        return cartItems.stream().filter(cartItem ->
                cartItem.getName()
                .equalsIgnoreCase(inventoryItem.getName()))
                .findFirst();
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(CartItem cartItem : cartItems){
            total = total.add(cartItemPriceCalculator.calculateItemPrice(cartItem));
        }
        return BigDecimalFormatter.formatForMoney(total);
    }
}
