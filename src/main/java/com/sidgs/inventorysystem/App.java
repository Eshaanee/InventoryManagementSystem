package com.sidgs.inventorysystem;

import com.sidgs.inventorysystem.enums.CommandEnum;
import com.sidgs.inventorysystem.service.InventoryService;
import com.sidgs.inventorysystem.service.InventoryServiceImpl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to Inventory Management System!");
        System.out.println("Please enter the following commands accordingly");
        showActionMessages();
        Scanner scanner = new Scanner(System.in);
        InventoryService inventoryService = new InventoryServiceImpl();
        boolean flag = true;
        do {
            String command = scanner.nextLine();
            String parameters[] = command.split(" ", 0);
            try {

                switch (CommandEnum.getEnum(parameters[0])) {
                    case CREATE:
                        double costPrice = Double.parseDouble(parameters[2]);
                        double sellingPrice = Double.parseDouble(parameters[3]);
                        inventoryService.create(parameters[1], costPrice, sellingPrice);
                        break;

                    case UPDATE_BUY:
                        int quantity = Integer.parseInt(parameters[2]);
                        inventoryService.updateBuy(parameters[1], quantity);
                        break;

                    case UPDATE_SELL:
                        int quantitySell = Integer.parseInt(parameters[2]);
                        inventoryService.updateSell(parameters[1], quantitySell);
                        break;

                    case DELETE:
                        inventoryService.delete(parameters[1]);
                        break;

                    case REPORT:
                        inventoryService.report();
                        break;

                    case UPDATE_SELL_PRICE:
                        double newSellingPrice = Double.parseDouble(parameters[2]);
                        inventoryService.updateSellPrice(parameters[1], newSellingPrice);
                        break;

                    case EXIT:
                        flag = false;
                        scanner.close();
                        break;
                }
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.print("Please enter the correct command\n");
                showActionMessages();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                showActionMessages();
            }
        } while (flag);
    }

    private static void showActionMessages() {
        System.out.println("1. To create a new item -> create itemName costPrice sellingPrice");
        System.out.println("2. To delete an existing item -> delete itemName");
        System.out.println("3. To update Buy an item -> updateBuy itemName quantity");
        System.out.println("4. To update Sell an item -> updateSell itemName quantity");
        System.out.println("5. Inventory Report -> report");
        System.out.println("0. Exit -> exit");
    }
}
