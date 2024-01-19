package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Catalog {
	private List<Item> catalog;
	
	
	public Catalog(List<Item> catalog) {
		this.catalog = catalog;
	}
	
	//sort catalog by price
	public void sortLeastExpensive() {
		 Collections.sort(catalog, new Comparator<Item>() {

		        public int compare(Item o1, Item o2) {
		           
		            return (int) (o1.getPrice() - (o2.getPrice()));
		        }
		    });
		 System.out.println("Sorting by price!");
	}
	
	public void sortMostExpensive() {
		 Collections.sort(catalog, new Comparator<Item>() {

		        public int compare(Item o1, Item o2) {
		           
		            return (int) (o2.getPrice() - (o1.getPrice()));
		        }
		    });
		 System.out.println("Sorting by price!");
	}
	
	//sort catalog by name
	public void sortByName() {
		 Collections.sort(catalog, new Comparator<Item>() {

		        public int compare(Item o1, Item o2) {
		          
		            return o1.getName().compareTo(o2.getName());
		        }
		    });
		 System.out.println("Sorting by name!");
	}
	
	//return list of items in catalog
	public List<Item> getCatalog(){
		return catalog;
	}
	
	public Item getItemById(int id){
		for (int i = 0; i < catalog.size(); i++)
		{
			if (catalog.get(i).getId() == id)
			{
				return catalog.get(i);
			}
		}
		
		return null;
	}
	
	public void clear() {
		this.catalog.clear();
	}
	
	public int size() {
		return this.catalog.size();
	}
	
	
	
	
}
