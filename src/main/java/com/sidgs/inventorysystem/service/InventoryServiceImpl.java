package com.sidgs.inventorysystem.service;
import com.sidgs.inventorysystem.modal.*;
import java.util.*;
import java.io.*;
import com.sidgs.inventorysystem.dao.InventoryDao;
import com.sidgs.inventorysystem.modal.Items;

public class InventoryServiceImpl implements InventoryService {

	private List<Items> items = new ArrayList<Items>();
	int quantityFinal;
	
	public void create(String itemName, double costPrice, double sellingPrice) {
		Items item = new Items(itemName, costPrice, sellingPrice, 0);
		items.add(item);
	}

	public void readInventory() {
		System.out.println("                 INVENTORY REPORT");
		System.out.println("Item Name            "+" Bought At "+"  Sold At "+"  AvailableQty "+"  Value");
		System.out.println("----------           "+"----------- "+"  ------- "+"  ------------ "+" ---------");
		double valueSum=0;
		for (Items item : items) {
			System.out.println(item.getItemName()+"                    "+item.getCostPrice()+"          "+item.getSellingPrice()+"      "+item.getQuantity()+ "            "+item.getQuantity() *item.getCostPrice());
			
			valueSum+=item.getQuantity() *item.getCostPrice();
		}
		System.out.println("----------------------------------------------------------------------------"); 
		System.out.println("Total Value                                                                  "+valueSum);
	}

	public void updateBuy(String itemName, int quantity) {
		// TODO Auto-generated method stub
		for (Items item : items) {
			if (item.getItemName().equalsIgnoreCase(itemName)) {
				//quantityFinal=item.getQuantity() + quantity;
		        item.setQuantity(item.getQuantity() + quantity);
		        return;
			}
		}
	}

	public void delete(String itemName) {
		// TODO Auto-generated method stub
		Iterator itr= items.iterator();
		for(int i=0;i<items.size();i++) {
			if (items.get(i).getItemName().equalsIgnoreCase(itemName)) {
				items.remove(i);
				return;
			}
		}

	}

	public void updateSell(String itemName, int quantitySell) {
		// TODO Auto-generated method stub
		for (Items item : items) {
			if (item.getItemName().equalsIgnoreCase(itemName)) {
				//quantityFinal=item.getQuantity() - quantitySell;
				item.setQuantity(item.getQuantity() - quantitySell);
			    return;
			}
		}
	}
}
