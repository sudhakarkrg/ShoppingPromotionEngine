package com.shop.impl;

import com.shop.Promotion;
import com.shop.model.Sku;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This is the specific promotion implementation of combination one quantity of skus for fixed price
 * Example : (1 items of SKU1 + 1 items of SKU2 = Fixed price)
 */
public class SingleQtySkusFixedPricePromotion implements Promotion {

    // List of sku names represents this promotion
    private List<String> skuNames;
    // The Fixed price of combination of single quantity of sku's
    private double totalPrice;

    private static final String PROMOTION_CODE = "COMBINE2SKUFOR30";

    public SingleQtySkusFixedPricePromotion(List<String> skuNames, double totalPrice) {
        this.skuNames = skuNames;
        this.totalPrice = totalPrice;
    }

    /**
     * Calculate total for all items after applying this promotion
     * @param itemsShoppedUsingThisPromotion Map of Items shopped under this promotion and total count shopped
     * @return total value after applying promotion
     */
    @Override
    public double calculateTotal(Map<Sku, Integer> itemsShoppedUsingThisPromotion) {
        Collection<Integer> allTotalItems = itemsShoppedUsingThisPromotion.values();
        // This is the common minimum qty shopped across all shopped items.
        // If only few items are present from the list of sku's for this promotion, then the commonMinimumQty will be 0.
        int commonMinimumQty = 0;
        if(itemsShoppedUsingThisPromotion.size() == skuNames.size()) {
            commonMinimumQty = findCommonMinimumQty(allTotalItems);
        }
        double total = commonMinimumQty * totalPrice;
        for(Map.Entry<Sku, Integer> productsPromotionEntry : itemsShoppedUsingThisPromotion.entrySet()) {
            Integer totalItems = productsPromotionEntry.getValue();
            if(totalItems == commonMinimumQty) {
                continue;
            }
            int remainingItems = totalItems - commonMinimumQty;
            Sku sku = productsPromotionEntry.getKey();
            double productPrice = sku.getPrice();
            total = total + (remainingItems * productPrice);
        }
        return total;
    }

    /**
     * Find the common minimum quantity from all sku's total items
     * @param allTotalItems total items of all skus shopped.
     * @return common minimum quantity shopped across all SKU's.
     */
    private int findCommonMinimumQty(Collection<Integer> allTotalItems ) {
        int commonMinimumQty = Integer.MAX_VALUE;
        for(Integer totalItem : allTotalItems) {
            if(totalItem < commonMinimumQty) {
                commonMinimumQty = totalItem;
            }
        }
        return commonMinimumQty;
    }

    public List<String> getSkuNames() {
        return skuNames;
    }

    public void setSkuNames(List<String> skuNames) {
        this.skuNames = skuNames;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
