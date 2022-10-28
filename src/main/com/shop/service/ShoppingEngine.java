package com.shop.service;

import com.shop.Promotion;
import com.shop.impl.PromotionMapper;
import com.shop.model.ShoppingCart;
import com.shop.model.Sku;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Processor for checking out cart items and compute cart total
 */
public class ShoppingEngine {

    /**
     * Calculates the cart total with input of cart items after applying different promotions
     * @param cart Object contains map of cart items
     * @return total cart value
     */
    public double checkOut(ShoppingCart cart) {
        Map<Sku, Integer> cartItems = cart.getCartItems();
        Set<Map.Entry<Sku, Integer>> cartItemEntries = cartItems.entrySet();
        // This map contains map of cart items mapped under each promotion's
        // Example map of ("promotioncode", map of all (skus under this promotion, cartTotal cart items))
        Map<String, Map<Sku, Integer>> promotionCartItemsBuckets = new HashMap<>();
        for(Map.Entry<Sku, Integer> cartItemEntry : cartItemEntries) {
            Sku sku = cartItemEntry.getKey();
            String promotionCode = PromotionMapper.getPromotionCode(sku.getSkuName());
            Map<Sku, Integer> shoppedItems = promotionCartItemsBuckets.get(promotionCode);
            if(shoppedItems != null) {
                shoppedItems.put(sku, cartItemEntry.getValue());
            } else {
                shoppedItems = new HashMap<>();
                shoppedItems.put(sku, cartItemEntry.getValue());
                promotionCartItemsBuckets.put(promotionCode, shoppedItems);
            }
        }
        return calculateCartTotal(promotionCartItemsBuckets);
    }

    /**
     * Combines total from each promotions mapped with shopped items and its qty
     * @param promotionCartItemsBuckets contains mapping of all cart items under the promotions available in the system
     * @return total after applying promotions
     */
    public double calculateCartTotal(Map<String, Map<Sku, Integer>> promotionCartItemsBuckets) {
        double cartTotal = 0;
        Set<Map.Entry<String, Map<Sku, Integer>>> promotionCartEntries = promotionCartItemsBuckets.entrySet();
        for(Map.Entry<String, Map<Sku, Integer>> promotionCartEntry : promotionCartEntries) {
            String promotionCode = promotionCartEntry.getKey();
            Promotion promotion = PromotionMapper.getPromotion(promotionCode);
            Map<Sku, Integer> itemUnderPromotion = promotionCartEntry.getValue();
            cartTotal = cartTotal + promotion.calculateTotal(itemUnderPromotion);
        }
        return cartTotal;
    }
}
