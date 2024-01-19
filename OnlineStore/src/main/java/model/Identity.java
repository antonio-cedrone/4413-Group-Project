package model;

import java.util.List;

public class Identity {
	private int id;
	private String user;
	private String pass;
	private String email;
	private String phone;
	private String firstName;
	private String lastName;
	private int addressId;
	private Address address;
	private List<Order> orders;
	private String creditCard;
	private String cvv;
	private String expiry;
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPass() {
		return this.pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public List<Order> getOrders(){
		return this.orders;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCreditCard() {
		return this.creditCard;
	}
	
	public void setCreditCard(String credit) {
		this.creditCard = credit;
	}
	
	public String getCvv() {
		return this.cvv;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getExpiry() {
		return this.expiry;
	}
	
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
}
