package dao;


import java.util.List;

import model.Address;

public interface AddressDAO {
	public Address getAddress(int addressID);
	public List<Address> getAllAddresses();
	public void updateAddress(int id, String street, String province, String city, String zip);
	public int newAddress(String street, String province, String city, String zip);
}
