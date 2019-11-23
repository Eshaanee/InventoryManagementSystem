package com.sidgs.inventorysystem.modal;

public class Items {
	
	private String itemName;
	private double costPrice;
	private double sellingPrice;
	private int quantity;
	
	public Items(String itemName, double costPrice, double sellingPrice, int quantity) {
		this.itemName= itemName;
		this.costPrice=costPrice;
		this.sellingPrice=sellingPrice;
		this.quantity=quantity;
	}
	
	
	public String getItemName() {
		return itemName;
	}
	


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}
	
}
