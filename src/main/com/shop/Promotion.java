package com.shop;

import com.shop.model.Sku;

import java.util.Map;

/**
 * Interface for all Promotion types.
 */
public interface Promotion {

    /**
     * implementation class should provide the logic of how promotion should be applied for given set of skus and its total shopped count
     * @param itemsShoppedUsingThisPromotion items shopped under this promotion
     * @return returns total for all items under this promotion
     */
    double calculateTotal(Map<Sku, Integer> itemsShoppedUsingThisPromotion);

}
