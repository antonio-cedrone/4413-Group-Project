package dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.sqlite.SQLiteDataSource;

import model.Catalog;
import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ItemDAOImpl implements ItemDAO{
	ServletContext cont;
	public ItemDAOImpl(ServletContext cont) {
		this.cont = cont;
	}
	
	//returns list of items in inventory
	public List<Item> getItems(){
		List<Item> inventory = null;
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			
			PreparedStatement statement = con.prepareStatement("select * from inventory");
			
			ResultSet resultSet =  statement.executeQuery();
			
			inventory = new ArrayList<Item>();
			while(resultSet.next()) {
				Item item = new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("category"), resultSet.getString("brand"), resultSet.getLong("price"), resultSet.getString("desc"), resultSet.getInt("quantity"), resultSet.getBytes("image"));
				inventory.add(item);
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
		
		return inventory;
	}
	
	//returns items of a certain category
	public List<Item> getItemsByCat(String cat){
		List<Item> inventory = null;
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			
			PreparedStatement statement = con.prepareStatement("select * from inventory where category = '"+cat+"'");
			System.out.println("select * from inventory where category = '"+cat+"'");
			//statement.setString(1, cat);
			
			ResultSet resultSet =  statement.executeQuery();
			
			inventory = new ArrayList<Item>();
			while(resultSet.next()) {
				Item item = new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("category"), resultSet.getString("brand"), resultSet.getLong("price"), resultSet.getString("desc"), resultSet.getInt("quantity"), resultSet.getBytes("image"));
				inventory.add(item);
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
		
		return inventory;
	}
	
	//returns items of a certain brand
	public List<Item> getItemsByBrand(String brand){
		List<Item> inventory = null;
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("select * from inventory where brand = '"+brand+"'");
			System.out.println("select * from inventory where brand = '"+brand+"'");
			//statement.setString(1, brand);
			
			ResultSet resultSet =  statement.executeQuery();
			
			inventory = new ArrayList<Item>();
			while(resultSet.next()) {
				Item item = new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("category"), resultSet.getString("brand"), resultSet.getLong("price"), resultSet.getString("desc"), resultSet.getInt("quantity"), resultSet.getBytes("image"));
				inventory.add(item);
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
		
		return inventory;
	}
	
	//decreases the quantity of items after an order
	public void decreaseQty(List<Item> order, Catalog catalog) {
        Connection con = null;

        try {
            con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();

            PreparedStatement statement = con.prepareStatement("update inventory set quantity = ? where id = ?");

            for (int i = 0; i < order.size(); i++) 
            {
                for (int j = 0; j < catalog.getCatalog().size(); j++)
                {
                    if (order.get(i).getId() == catalog.getCatalog().get(j).getId())
                    {
                        statement.setInt(1, catalog.getCatalog().get(j).getQty() - order.get(i).getQty());
                        statement.setInt(2, order.get(i).getId());

                        statement.execute();

                        catalog.getCatalog().get(j).setQty(catalog.getCatalog().get(j).getQty() - order.get(i).getQty());
                        break;
                    }
                }
            }
        }
        catch(SQLException e)
        {
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
	
	public void updateQty(int id, int qty) {
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("update inventory set quantity = "+qty+" where id = "+id);
			statement.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally
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
}