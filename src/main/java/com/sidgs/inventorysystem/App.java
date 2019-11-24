package com.sidgs.inventorysystem;

import java.io.*;
import java.util.*;

import com.sidgs.inventorysystem.modal.Items;
import com.sidgs.inventorysystem.service.InventoryService;
import com.sidgs.inventorysystem.service.InventoryServiceImpl;
import com.sidgs.inventorysystem.modal.*;

public class App {
	public static void main(String[] args) {
		System.out.println("hi");
		Scanner s = new Scanner(System.in);
		InventoryService invService = new InventoryServiceImpl();
		boolean flag = true;
		do {
			String input = s.nextLine();
			String splitAr[] = input.split(" ", 0);
			switch (splitAr[0]) {
			case "create":
				double costPrice = Double.parseDouble(splitAr[2]);
				double sellingPrice = Double.parseDouble(splitAr[3]);
				invService.create(splitAr[1], costPrice, sellingPrice);
				break;

			case "updateBuy":
				int quantity = Integer.parseInt(splitAr[2]);
				invService.updateBuy(splitAr[1], quantity);		
				break;
				
			case "updateSell":
				int quantitySell = Integer.parseInt(splitAr[2]);
				invService.updateSell(splitAr[1], quantitySell);		
				break;
			
			case "delete":
				invService.delete(splitAr[1]);
				break;
				
			case "report":
				invService.readInventory();		
				break;
				
			case "exit" :
			     flag = false;
			     break;
			}
		} while (flag); // while ends
	}

	

}
