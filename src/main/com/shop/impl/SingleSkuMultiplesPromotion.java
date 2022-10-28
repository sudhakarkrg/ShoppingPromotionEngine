package com.shop.impl;

import com.shop.Promotion;
import com.shop.model.Sku;

import java.util.Map;

/**
 * This is the specific promotion implementation of multiple of one SKU for a fixed price
 * Example : (x items of SKU1 = Fixed price)
 */
public class SingleSkuMultiplesPromotion implements Promotion {

    // Represents how many items in one SKU
    private int multiples;
    // The Fixed price for above multiples
    private double totalPrice;

    public SingleSkuMultiplesPromotion(int multiples, double totalPrice) {
        this.multiples = multiples;
        this.totalPrice = totalPrice;
    }

    /**
     * Calculate total for all items after applying this promotion
     * @param itemsShoppedUsingThisPromotion Items shopped under this promotion
     * @return total value after applying promotion
     */
    @Override
    public double calculateTotal(Map<Sku, Integer> itemsShoppedUsingThisPromotion) {
        double total = 0;
        for(Map.Entry<Sku, Integer> productsEntry : itemsShoppedUsingThisPromotion.entrySet()) {
            Sku sku = productsEntry.getKey();
            double productPrice = sku.getPrice();
            Integer totalItems = productsEntry.getValue();
            int noOfMultiples = totalItems / multiples;
            int remainingItems = totalItems % multiples;
            total = total + (noOfMultiples * totalPrice) + (remainingItems * productPrice);
        }
        return total;
    }

    public int getMultiples() {
        return multiples;
    }

    public void setMultiples(int multiples) {
        this.multiples = multiples;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
