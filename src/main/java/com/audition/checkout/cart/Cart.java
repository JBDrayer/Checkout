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
        Optional<CartItem> optionalCartItem = getItemFromCart(inventoryItem.getName());
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = cartItems.get(cartItems.indexOf(optionalCartItem.get()));
            cartItem.incrementQuantity();
        } else {
            cartItems.add(new CartItem(inventoryItem));
        }
    }

    public void removeItem(String itemName) {
        Optional<CartItem> optionalCartItem = getItemFromCart(itemName);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            if (cartItem.getQuantity() > 1) {
                cartItem.decrementQuantity();
            } else {
                cartItems.remove(optionalCartItem.get());
            }
        } else {
            throw new CartItemNotFoundException(itemName + " not found in cart");
        }
    }

    public void addWeightedItem(InventoryItem inventoryItem, BigDecimal weight) {
        Optional<CartItem> optionalCartItem = getItemFromCart(inventoryItem.getName());
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.addWeight(weight);
        } else {
            CartItem cartItem = new CartItem(inventoryItem);
            cartItem.addWeight(weight);
            cartItems.add(cartItem);
        }
    }

    public void removeWeightedItem(String itemName, BigDecimal weight) {
        Optional<CartItem> optionalCartItem = getItemFromCart(itemName);
        if(optionalCartItem.isPresent()){
            CartItem cartItem = optionalCartItem.get();
            cartItem.removeWeight(weight);
            if(cartItem.getWeight().compareTo(BigDecimal.ZERO) <= 0){
                cartItems.remove(optionalCartItem.get());
            }
        }
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            total = total.add(cartItemPriceCalculator.calculateItemPrice(cartItem));
        }
        return BigDecimalFormatter.formatForMoney(total);
    }

    private Optional<CartItem> getItemFromCart(String itemName) {
        return cartItems.stream().filter(cartItem ->
                cartItem.getName()
                        .equalsIgnoreCase(itemName))
                .findFirst();
    }
}
