package dao;

import java.util.List;

import model.Catalog;
import model.Item;

public interface ItemDAO {
	public List<Item> getItems();
	public List<Item> getItemsByCat(String cat);
	public List<Item> getItemsByBrand(String brand);
	public void decreaseQty(List<Item> order, Catalog catalog);
	public void updateQty(int id, int newQty);
}
