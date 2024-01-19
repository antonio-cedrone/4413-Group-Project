package model;

import java.util.List;
import java.util.ArrayList;

public class Cart {
	private List<Item> cart;
	
	public Cart() {
		cart = new ArrayList<Item>();
	}
	
	//if item type is already in cart, simply increment the quantity. Otherwise, add the item to cart
	public void add(Item item) {
		for(Item i : this.cart) {
			if(i.getId() == item.getId()) {
				int qty = i.getQty();
				i.setQty(qty + item.getQty());
				return;
			}
		}
		this.cart.add(new Item(item.getId(), item.getName(), item.getCategory(), item.getBrand(), item.getPrice(),  item.getDesc(), item.getQty(), item.getImage()));
	}
	
	//remove item from cart given specific id
	public void remove(int id) {
		for(Item i : this.cart) {
			if(i.getId() == id) {
				this.cart.remove(i);
				return;
			}
		}
	}
	
	public int size() {
		return this.cart.size();
	}
	

	public List<Item> getCart()
	{
		return cart;
	}
	
	public void updateQty(int id, int qty) {
		for(Item i : this.cart) {
			if(i.getId() == id) {
				i.setQty(qty);
				return;
			}
		}
	}
}
