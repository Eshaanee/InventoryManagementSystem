package com.sidgs.inventorysystem;

import java.io.*;
import java.util.*;

import com.sidgs.inventorysystem.modal.Items;
import com.sidgs.inventorysystem.service.InventoryService;
import com.sidgs.inventorysystem.service.InventoryServiceImpl;
import com.sidgs.inventorysystem.modal.*;

public class App {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("hi");
		InventoryService invService = new InventoryServiceImpl();
		boolean flag = true;
		do {
			String input = s.nextLine();
			String splitAr[] = input.split(" ", 0);
			/*
			 * for (String splitted : splitAr) { System.out.println(splitted); }
			 */
			switch (splitAr[0]) {
			case "create":
				//crSystem.out.println("inside create block");
				double firstInt = Double.parseDouble(splitAr[2]);
				double secondInt = Double.parseDouble(splitAr[3]);
				invService.create(splitAr[1], firstInt, secondInt);
				break;

			case "updateBuy":
				//System.out.println("inside updateBuy block");
				int quantity = Integer.parseInt(splitAr[2]);
				invService.updateBuy(splitAr[1], quantity);		
				break;
				
			case "read":
				invService.readInventory();		
				break;
				
			case "exit" :
			     flag = false;
			     break;

			/*
			 * case "updateSell" : invService.create(secondWord,firstInt,secondInt); break;
			 * case "delete" : invService.create(secondWord,firstInt,secondInt); break;
			 */
			}
		} while (flag); // while ends
	}

	/*
	 * private determiningWhichMethodTocallOnTheBasisOfUserInput() {
	 * 
	 * }
	 */

}
