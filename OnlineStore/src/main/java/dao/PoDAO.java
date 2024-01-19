package dao;


import java.util.List;

import model.Order;

public interface PoDAO {
	public List<Order> getPOs(int customerID);
	public void insertPO(int customerID, Order o);
	public List<Order> getAllOrders();
}
