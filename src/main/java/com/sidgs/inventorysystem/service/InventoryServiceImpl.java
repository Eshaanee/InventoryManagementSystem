package com.sidgs.inventorysystem.service;

import com.sidgs.inventorysystem.dao.InventoryDao;
import com.sidgs.inventorysystem.dao.InventoryDaoImpl;
import com.sidgs.inventorysystem.model.Inventory;
import com.sidgs.inventorysystem.model.Item;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements InventoryService {

    private final String REPORT_TITLE = "\t\t\t INVENTORY REPORT";
    private final String ITEM_NAME_COLUMN_TITLE = "Item Name\t\t";
    private final String BOUGHT_AT_COLUMN_TITLE = "Bought At\t\t";
    private final String SOLD_AT_COLUMN_TITLE = "Sold At\t\t\t";
    private final String AVAILABLE_QTY_COLUMN_TITLE = "AvailableQty\t\t";
    private final String VALUE_COLUMN_TITLE = "Value\t\t";
    private final String COLUMN_TITLE_SEPARATOR = "---------\t\t";
    private final String TOTAL_VALUE_FIELD_TITLE = "Total Value\t\t\t\t\t\t\t\t\t\t\t";
    private final String PROFIT_SINCE_LAST_REPORT_FIELD_TITLE = "Profit since last report\t\t\t\t\t\t\t\t\t";
    private InventoryDao inventoryDao = new InventoryDaoImpl();

    public void create(String itemName, double costPrice, double sellingPrice) {
        if (inventoryDao.getItem(itemName) != null) {
            throw new IllegalStateException("Cannot create. Item already exits");
        }
        Item item = new Item(itemName, costPrice, sellingPrice, 0);
        inventoryDao.create(item);
    }

    public void updateBuy(String itemName, int quantity) {
        Item item = inventoryDao.getItem(itemName);
        if (item == null) {
            throw new NoSuchElementException("Cannot update buy. Item doesn't exits. Please create the item first");
        }
        int currentQuantity = item.getQuantity();
        item.setQuantity(currentQuantity + quantity);
    }

    public void delete(String itemName) {
        Item item = inventoryDao.getItem(itemName);
        if (item == null) {
            throw new NoSuchElementException("Cannot delete item. Item doesn't exits.");
        }
        inventoryDao.delete(item);
    }

    public void updateSell(String itemName, int quantitySell) {
        Item item = inventoryDao.getItem(itemName);
        if (item == null) {
            throw new NoSuchElementException("Cannot update sell. Item doesn't exits. Please create the item first");
        }
        int currentQuantity = item.getQuantity();
        if (currentQuantity < quantitySell) {
            throw new IllegalStateException("Cannot update sell. Insufficient quantity exists to sell");
        }
        item.setQuantity(currentQuantity - quantitySell);
        calculateProfit(quantitySell, item);
    }

    private void calculateProfit(int quantitySell, Item item) {
        Double currentProfit = Inventory.getInventoryInstance().getCurrentProfit();
        currentProfit += quantitySell * (item.getSellingPrice() - item.getCostPrice());
        Inventory.getInventoryInstance().setCurrentProfit(currentProfit);
    }

    public void updateSellPrice(String itemName, double sellPrice) {
        Item item = inventoryDao.getItem(itemName);
        if (item == null) {
            throw new NoSuchElementException("Cannot update sell. Item doesn't exist. Please create the item first");
        }
        item.setSellingPrice(sellPrice);
    }

    public void report() {
        if (Inventory.getInventoryInstance().getCurrentItemMap().isEmpty()) {
            throw new NoSuchElementException("No data registered in the inventory!");
        } else {
            Map<String, Item> sortedCurrentMap = Inventory.getInventoryInstance().getCurrentItemMap().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            printItemMap(sortedCurrentMap);
            Inventory.getInventoryInstance().setLastProfit(Inventory.getInventoryInstance().getCurrentProfit());
            Inventory.getInventoryInstance().setCurrentProfit(0.0);
            Inventory.getInventoryInstance().setCurrentTotal(0.0);
        }
    }

    private void printItemMap(Map<String, Item> map) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        System.out.println(REPORT_TITLE);
        System.out.print(ITEM_NAME_COLUMN_TITLE);
        System.out.print(BOUGHT_AT_COLUMN_TITLE);
        System.out.print(SOLD_AT_COLUMN_TITLE);
        System.out.print(AVAILABLE_QTY_COLUMN_TITLE);
        System.out.println(VALUE_COLUMN_TITLE);
        System.out.print(COLUMN_TITLE_SEPARATOR);
        System.out.print(COLUMN_TITLE_SEPARATOR);
        System.out.print(COLUMN_TITLE_SEPARATOR);
        System.out.print(COLUMN_TITLE_SEPARATOR);
        System.out.println(COLUMN_TITLE_SEPARATOR);
        map.forEach((itemName, i) -> {
            Item item = i;
            System.out.print(itemName + "\t\t\t");
            System.out.print(decimalFormat.format(item.getCostPrice()) + "\t\t\t");
            System.out.print(decimalFormat.format(item.getSellingPrice()) + "\t\t\t");
            System.out.print(item.getQuantity() + "\t\t\t");
            System.out.println(decimalFormat.format(item.getCostPrice() * item.getQuantity()));
            Double currentTotal = Inventory.getInventoryInstance().getCurrentTotal();
            currentTotal += item.getCostPrice() * item.getQuantity();
            Inventory.getInventoryInstance().setCurrentTotal(currentTotal);
        });

        System.out.println(COLUMN_TITLE_SEPARATOR + COLUMN_TITLE_SEPARATOR + COLUMN_TITLE_SEPARATOR + COLUMN_TITLE_SEPARATOR + COLUMN_TITLE_SEPARATOR);
        System.out.println(TOTAL_VALUE_FIELD_TITLE + " " + decimalFormat.format(Inventory.getInventoryInstance().getCurrentTotal()));
        System.out.println(PROFIT_SINCE_LAST_REPORT_FIELD_TITLE + " " + decimalFormat.format(Inventory.getInventoryInstance().getCurrentProfit()));
        System.out.println("\n");
    }
}
