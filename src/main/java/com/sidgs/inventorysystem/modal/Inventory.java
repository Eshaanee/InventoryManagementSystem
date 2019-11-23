package com.sidgs.inventorysystem.modal;

import java.util.List;

public class Inventory {
	private String name;
	private List<Items> items;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}

}
