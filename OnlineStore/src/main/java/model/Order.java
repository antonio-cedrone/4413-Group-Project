package model;

import java.util.List;

public class Order {
	//list of item ids
	private  List<Amount> order;
	private String dateOfPurchase;
	
	public Order() {}
	public Order(List<Amount> order, String dateOfPurchase)
	{
		this.order = order;
		this.dateOfPurchase = dateOfPurchase;
	}

	public List<Amount> getOrder() {
		return order;
	}

	public void setOrder(List<Amount> order) {
		this.order = order;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	//order consists of list of items

}
