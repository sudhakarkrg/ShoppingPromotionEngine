package com.shop.impl;

import com.shop.model.ShoppingCart;
import com.shop.model.Sku;
import com.shop.service.ShoppingEngine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class of testing whole promotion engine with all different kind of promotions.
 */
class PromotionEngineTest {
    private static Sku skuA;
    private static Sku skuB;
    private static Sku skuC;
    private static Sku skuD;
    private static Map<Sku, Integer> cartItems;
    private static ShoppingEngine shoppingEngine;
    private static ShoppingCart cart;

    @BeforeAll
    static void setUpOnce() {
        skuA = new Sku("A", 50);
        skuB = new Sku("B", 30);
        skuC = new Sku("C", 20);
        skuD = new Sku("D", 15);
        PromotionMapper.addPromotion("GET3FOR130", new SingleSkuMultiplesPromotion(3,130));
        PromotionMapper.addPromotion("GET2FOR45", new SingleSkuMultiplesPromotion(2,45));
        List<String> combine2Skus = new ArrayList<>();
        combine2Skus.add("C");
        combine2Skus.add("D");
        PromotionMapper.addPromotion("COMBINE2SKUFOR30", new SingleQtySkusFixedPricePromotion(combine2Skus, 30));
        cartItems = new HashMap<>();
        shoppingEngine = new ShoppingEngine();
        cart = new ShoppingCart();
        PromotionMapper.addSkuPromotionCode("A","GET3FOR130");
        PromotionMapper.addSkuPromotionCode("B","GET2FOR45");
        PromotionMapper.addSkuPromotionCode("C","COMBINE2SKUFOR30");
        PromotionMapper.addSkuPromotionCode("D","COMBINE2SKUFOR30");
    }

    @BeforeEach
    void setup() {
        cartItems.clear();
    }

    @Test
    void testCalculateTotalForScenario1() {
        cartItems.put(skuA, 1);
        cartItems.put(skuB, 1);
        cartItems.put(skuC, 1);
        cart.setCartItems(cartItems);
        assertEquals(100,shoppingEngine.checkOut(cart));
    }

    @Test
    void testCalculateTotalForScenario2() {
        cartItems.put(skuA, 5);
        cartItems.put(skuB, 5);
        cartItems.put(skuC, 1);
        cart.setCartItems(cartItems);
        assertEquals(370,shoppingEngine.checkOut(cart));
    }

    @Test
    void testCalculateTotalForScenario3() {
        cartItems.put(skuA, 3);
        cartItems.put(skuB, 5);
        cartItems.put(skuC, 1);
        cartItems.put(skuD, 1);
        cart.setCartItems(cartItems);
        assertEquals(280,shoppingEngine.checkOut(cart));
    }
}