package com.shop.impl;

import com.shop.model.Sku;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleQtySkusFixedPricePromotionTest {
    private static Sku skuC;
    private static Sku skuD;
    private static SingleQtySkusFixedPricePromotion singleQtySkusFixedPricePromotion;
    // Map represents sku shopped and total Qty shopped
    private static Map<Sku, Integer> itemsShoppedUsingThisPromotion;

    @BeforeAll
    static void setUpOnce() {
        skuC = new Sku("C", 25);
        skuD = new Sku("D", 20);
        List<String> combine2Skus = new ArrayList<>();
        combine2Skus.add("C");
        combine2Skus.add("D");
        singleQtySkusFixedPricePromotion = new SingleQtySkusFixedPricePromotion(combine2Skus, 30);
        itemsShoppedUsingThisPromotion = new HashMap<>();
    }

    @BeforeEach
    void setup() {
        itemsShoppedUsingThisPromotion.clear();
    }

    @Test
    void testWithFirstSkuOneQty() {
        itemsShoppedUsingThisPromotion.put(skuC, 1);
        assertEquals(25, singleQtySkusFixedPricePromotion.calculateTotal(itemsShoppedUsingThisPromotion));
    }

    @Test
    void testWithSecondSkuOneQty() {
        itemsShoppedUsingThisPromotion.put(skuD, 1);
        assertEquals(20, singleQtySkusFixedPricePromotion.calculateTotal(itemsShoppedUsingThisPromotion));
    }

    @Test
    void testWithTwoSkuOneQty() {
        itemsShoppedUsingThisPromotion.put(skuC, 1);
        itemsShoppedUsingThisPromotion.put(skuD, 1);
        assertEquals(30, singleQtySkusFixedPricePromotion.calculateTotal(itemsShoppedUsingThisPromotion));
    }

    @Test
    void testWithTwoSkuMultipleQty() {
        itemsShoppedUsingThisPromotion.put(skuC, 2);
        itemsShoppedUsingThisPromotion.put(skuD, 1);
        assertEquals(55, singleQtySkusFixedPricePromotion.calculateTotal(itemsShoppedUsingThisPromotion));
    }
}