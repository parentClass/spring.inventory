package com.parentclass.inventory.services;


import com.parentclass.inventory.models.Stock;

public interface StockService {
    /**
     * Persist a stock record
     * @param stock - stock object to be persisted
     * @return Stock
     */
    public Stock create(Stock stock);

    /**
     * Stock to modify its existing record
     * @param id - id of the stock
     * @param stock - stock object to be used as the basis of the updated record
     * @return Stock
     */
    public Stock update(long id, Stock stock);
}
