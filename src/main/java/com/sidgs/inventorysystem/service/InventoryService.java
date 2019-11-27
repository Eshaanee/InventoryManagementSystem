package com.sidgs.inventorysystem.service;

public interface InventoryService {

    void create(String itemName, double costPrice, double sellingPrice);

    void updateBuy(String itemName, int quantity);

    void delete(String itemName);

    void updateSell(String itemName, int quantity);

    void report();

    void updateSellPrice(String itemName, double sellPrice);
}
