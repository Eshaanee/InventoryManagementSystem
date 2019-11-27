package com.sidgs.inventorysystem.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static Inventory instance = null;
    public Double currentTotal = 0.0;
    public Double lastTotal = 0.0;
    public Double currentProfit = 0.0;
    public Double lastProfit = 0.0;
    private Map<String, Item> currentItemMap = new HashMap<>();
    private String name;

    private Inventory() {
    }

    public static synchronized Inventory getInventoryInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public Double getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(Double currentTotal) {
        this.currentTotal = currentTotal;
    }

    public Double getLastTotal() {
        return lastTotal;
    }

    public void setLastTotal(Double lastTotal) {
        this.lastTotal = lastTotal;
    }

    public Double getLastProfit() {
        return lastProfit;
    }

    public void setLastProfit(Double lastProfit) {
        this.lastProfit = lastProfit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Item> getCurrentItemMap() {
        return currentItemMap;
    }

    public void setCurrentItemMap(Map<String, Item> currentItemMap) {
        this.currentItemMap = currentItemMap;
    }

    public Double getCurrentProfit() {
        return currentProfit;
    }

    public void setCurrentProfit(Double currentProfit) {
        this.currentProfit = currentProfit;
    }

}
