package com.shop.model;

/**
 * Model representing any sku
 */
public class Sku {
    // Name of the SKU
    private String skuName;
    // Price of SKU for one quantity
    private double price;

    public Sku(String skuName, double price) {
        this.skuName = skuName;
        this.price = price;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sku Name:"+skuName+", Price:"+price;
    }
}
