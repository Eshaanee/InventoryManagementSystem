package com.sidgs.inventorysystem.dao;

import com.sidgs.inventorysystem.model.Inventory;
import com.sidgs.inventorysystem.model.Item;

public class InventoryDaoImpl implements InventoryDao {

    @Override
    public void create(Item item) {
        Inventory.getInventoryInstance().getCurrentItemMap().put(item.getItemName(), item);
    }

    public Item getItem(String itemName) {
        return Inventory.getInventoryInstance().getCurrentItemMap().get(itemName);
    }

    public boolean isItemExits(String itemName) {
        return Inventory.getInventoryInstance().getCurrentItemMap().containsKey(itemName);
    }

    @Override
    public void updateItem(Item item) {
        Inventory.getInventoryInstance().getCurrentItemMap().put(item.getItemName(), item);
    }

    @Override
    public void delete(Item item) {
        Inventory.getInventoryInstance().getCurrentItemMap().remove(item.getItemName());
    }
}
