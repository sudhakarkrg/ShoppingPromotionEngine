package com.shop.model;

import java.util.Map;

/**
 * Model object which posses cart items
 */
public class ShoppingCart {

    // Map of skus and no of items shopped
    private Map<Sku, Integer> cartItems;

    public Map<Sku, Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Sku, Integer> cartItems) {
        this.cartItems = cartItems;
    }
}
