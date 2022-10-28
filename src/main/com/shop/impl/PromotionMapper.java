package com.shop.impl;

import com.shop.Promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains important mapping between promotion, promotion object and sku.
 */
public class PromotionMapper {
    // Map of Promotion Code and Promotion Object
    private static Map<String, Promotion> promotions = new HashMap<>();
    // Map of SKU, promotion Code
    private static Map<String, String> skuPromotionMap = new HashMap<>();

    public static Promotion getPromotion(String promotionCode) {
        return promotions.get(promotionCode);
    }

    public static String getPromotionCode(String skuName) {
        return skuPromotionMap.get(skuName);
    }

    public static void addPromotion(String promotionCode, Promotion promotion) {
        promotions.put(promotionCode, promotion);
    }

    public static void addSkuPromotionCode(String skuName, String promotionCode) {
        skuPromotionMap.put(skuName, promotionCode);
    }

    public static Map<String, Promotion> getPromotions() {
        return promotions;
    }

    public static void setPromotions(Map<String, Promotion> promotions) {
        PromotionMapper.promotions = promotions;
    }

    public static Map<String, String> getSkuPromotionMap() {
        return skuPromotionMap;
    }

    public static void setSkuPromotionMap(Map<String, String> skuPromotionMap) {
        PromotionMapper.skuPromotionMap = skuPromotionMap;
    }
}
