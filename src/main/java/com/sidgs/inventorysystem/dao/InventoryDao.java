package com.sidgs.inventorysystem.dao;

import com.sidgs.inventorysystem.model.Item;

public interface InventoryDao {
    void create(Item item);

    Item getItem(String itemName);

    void delete(Item itemName);

    void updateItem(Item item);

    boolean isItemExits(String itemName);

}
