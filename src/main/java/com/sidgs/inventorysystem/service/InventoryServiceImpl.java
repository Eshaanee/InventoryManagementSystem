package com.sidgs.inventorysystem.service;

import java.util.*;
import java.io.*;
import com.sidgs.inventorysystem.dao.InventoryDao;
import com.sidgs.inventorysystem.modal.Items;

public class InventoryServiceImpl implements InventoryService {

	private List<Items> items = new ArrayList<Items>();

	public void create(String itemName, double costPrice, double sellingPrice) {
		Items item = new Items(itemName, costPrice, sellingPrice, 1);
		items.add(item);
	}

	public void readInventory() {
		for (Items item : items) {
			//System.out.println("inside arraylist");
			System.out.println("Item name " + item.getItemName());
			System.out.println("Cost Price " + item.getCostPrice());
			System.out.println("Selling Price " + item.getSellingPrice());
			System.out.println("Quantity " + item.getQuantity());
		}
	}

	public void updateBuy(String itemName, int quantity) {
		// TODO Auto-generated method stub
		//System.out.println("in updatebuy method");
		for (Items item : items) {
			if (item.getItemName().equalsIgnoreCase(itemName)) {
				//System.out.println("if blockate ");
				item.setQuantity(item.getQuantity() + quantity);
				//System.out.println(item.getQuantity());
				return;
			}
		}
	}

	public void delete(String itemName) {
		// TODO Auto-generated method stub

	}

	public void updateSell(String itemName, int quantity) {
		// TODO Auto-generated method stub
		/*
		 * item.setItemName(itemName); quantity-=quantity; item.setQuantity(quantity);
		 */
	}
}
