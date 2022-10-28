package com.shop.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.shop.model.Sku;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class SingleSkuMultiplesPromotionTest {
    private static Sku skuA;
    private static SingleSkuMultiplesPromotion singleSkuMultiplesPromotionType;
    private static Map<Sku, Integer> itemsShoppedUsingThisPromotion;

    @BeforeAll
    static void setUpOnce() {
        skuA = new Sku("A", 60);
        singleSkuMultiplesPromotionType = new SingleSkuMultiplesPromotion(3,130);
        itemsShoppedUsingThisPromotion = new HashMap<>();
    }

    @BeforeEach
    void setup() {
        itemsShoppedUsingThisPromotion.clear();
    }

    @Test
    void testSingleSkuMultiplesPromotionForOneQty() {
        itemsShoppedUsingThisPromotion.put(skuA, 1);
        assertEquals(60, singleSkuMultiplesPromotionType.calculateTotal(itemsShoppedUsingThisPromotion));
    }

    @Test
    void testSingleSkuMultiplesPromotionForExactMultiples() {
        itemsShoppedUsingThisPromotion.put(skuA, 3);
        assertEquals(130, singleSkuMultiplesPromotionType.calculateTotal(itemsShoppedUsingThisPromotion));
    }

    @Test
    void testSingleSkuMultiplesPromotionForMoreThanExactMultiples() {
        itemsShoppedUsingThisPromotion.put(skuA, 5);
        assertEquals(250, singleSkuMultiplesPromotionType.calculateTotal(itemsShoppedUsingThisPromotion));
    }
}