package com.sidgs.inventorysystem.service;

import java.util.*;
import com.sidgs.inventorysystem.modal.Items;

public interface InventoryService {
	
	void create(String itemName, double costPrice, double sellingPrice);
	void updateBuy(String itemName,int quantity);
	void delete(String itemName);
	void updateSell(String itemName, int quantity);
	void readInventory();
}
