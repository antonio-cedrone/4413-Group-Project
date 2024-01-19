package dao;

import java.sql.Connection;
import model.Catalog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.sqlite.SQLiteDataSource;

import model.Order;
import model.Amount;

public class PoDAOImpl implements PoDAO{
	ServletContext cont;
	public PoDAOImpl(ServletContext cont) {
		this.cont = cont;
	}
	@Override
	public List<Order> getPOs(int customerID) {
		List<Order> order = null;
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("select * from customerPO where customerId = ?");
			
			statement.setInt(1, customerID);
			
			ResultSet resultSet =  statement.executeQuery();
			
			Map<Integer, String> po = new HashMap<Integer, String>();
			while(resultSet.next()) {
				po.put(resultSet.getInt("PoId"), resultSet.getString("dateOfPurchase"));
			}
			
			order = new ArrayList<Order>();
			
			for (Map.Entry<Integer, String> entry : po.entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    
			    PreparedStatement statement2 = con.prepareStatement("select * from PO where Id = ?");
				
				statement2.setInt(1, key);
				
				ResultSet resultSet2 =  statement2.executeQuery();
				
				List<Amount> poIds = new ArrayList<Amount>();
				
				while (resultSet2.next())
				{
					Catalog cat = (Catalog) cont.getAttribute("catalog");
										
					poIds.add(new Amount(resultSet2.getInt("itemID"), cat.getItemById(resultSet2.getInt("itemID")).getName(), resultSet2.getInt("amount")));
				}
				
				order.add(new Order(poIds, value));
			}
			//con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			if (con != null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return order;
	}
	
	public void insertPO(int customerID, Order o) {
		Connection con = null;
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("insert into CustomerPO (customerId, dateOfPurchase) values (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, customerID);
			statement.setString(2, o.getDateOfPurchase());
			
			statement.execute();
			
			ResultSet r = statement.getGeneratedKeys();
			
			int key=-1;
			
			while(r.next())
			{
				key = r.getInt(1);
			}
			
			for (int i = 0; i < o.getOrder().size(); i++)
			{
				PreparedStatement statement2 = con.prepareStatement("insert into PO (id, itemId, amount) values (?, ?, ?)");
				
				statement2.setInt(1, key);
				statement2.setInt(2, o.getOrder().get(i).getId());
				statement2.setInt(3, o.getOrder().get(i).getAmount());
				
				statement2.execute();
			}
			//con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			if (con != null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<Order> getAllOrders(){
		List<Order> order = null;
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("select * from customerPO");
			
			
			
			ResultSet resultSet =  statement.executeQuery();
			
			Map<Integer, String> po = new HashMap<Integer, String>();
			while(resultSet.next()) {
				po.put(resultSet.getInt("PoId"), resultSet.getString("dateOfPurchase"));
			}
			
			order = new ArrayList<Order>();
			
			for (Map.Entry<Integer, String> entry : po.entrySet()) {
			    Integer key = entry.getKey();
			    String value = entry.getValue();
			    
			    PreparedStatement statement2 = con.prepareStatement("select * from PO where Id = ?");
				
				statement2.setInt(1, key);
				
				ResultSet resultSet2 =  statement2.executeQuery();
				
				List<Amount> poIds = new ArrayList<Amount>();
				
				while (resultSet2.next())
				{
					Catalog cat = (Catalog) cont.getAttribute("catalog");
										
					poIds.add(new Amount(resultSet2.getInt("itemID"), cat.getItemById(resultSet2.getInt("itemID")).getName(), resultSet2.getInt("amount")));
				}
				
				order.add(new Order(poIds, value));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			if (con != null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return order;
	}
	
}