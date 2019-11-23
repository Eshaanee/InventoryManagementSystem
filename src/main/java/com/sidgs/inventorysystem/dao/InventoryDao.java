package com.sidgs.inventorysystem.dao;

public interface InventoryDao {
	void create(String name, double costPrice);
	void updateBuy();
	void delete();
}
