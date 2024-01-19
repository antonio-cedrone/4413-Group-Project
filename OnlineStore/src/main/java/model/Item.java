package model;

public class Item {
	private int id;
	private String name;
	private String category;
	private String brand;
	private double price;
	private String desc;
	private int qty;
	private byte[] image;
	
	
	
	public Item (int id, String name, String category, String brand, double price, String desc, int qty, byte[] image)
	{
		this.id = id;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.desc = desc;
		this.qty = qty;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] blob) {
		this.image = blob;
	}

}
