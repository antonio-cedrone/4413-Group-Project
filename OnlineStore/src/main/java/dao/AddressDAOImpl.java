package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.sqlite.SQLiteDataSource;

import model.Address;
import model.Identity;


public class AddressDAOImpl implements AddressDAO{
	ServletContext cont;
	public AddressDAOImpl(ServletContext cont) {
		this.cont = cont;
	}
	@Override
	public Address getAddress(int addressID) {
		Address address = null;
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			
			PreparedStatement statement = con.prepareStatement("select * from address where id = ?");
			
			statement.setInt(1, addressID);
			
			ResultSet resultSet =  statement.executeQuery();
			
			address = new Address();

			resultSet.next();
			
			address.setId(resultSet.getInt("id"));
			address.setCity(resultSet.getString("city"));
			address.setProvince(resultSet.getString("province"));
			address.setStreet(resultSet.getString("street"));
			address.setZip(resultSet.getString("zip"));
			
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
		
		return address;
	}
	
	public void updateAddress(int id, String street, String province, String city, String zip) {
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("update address set street = ?, province = ?, city = ?, zip = ? where id = ?");
            statement.setString(1, street);
            statement.setString(2, province);
            statement.setString(3, city);
            statement.setString(4, zip);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
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
	
	public int newAddress(String street, String province, String city, String zip) {
    	Connection con = null;
		int key = -1;
    	
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("insert into Address(street, province, city, zip) values (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, street);
            statement.setString(2, province);
            statement.setString(3, city);
            statement.setString(4, zip);

            statement.execute();
            
            ResultSet r = statement.getGeneratedKeys();
            
            while (r.next())
            {
            	key = r.getInt(1);
            }
			
        } catch (SQLException e) {
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
		
		return key;
    }
	@Override
	public List<Address> getAllAddresses() {
		List<Address> addresses = new ArrayList<>();        
        Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("select * from address");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Address address = new Address();
            	address.setId(resultSet.getInt("id"));
                address.setCity(resultSet.getString("city"));
                address.setProvince(resultSet.getString("province"));
                address.setStreet(resultSet.getString("street"));
				address.setZip(resultSet.getString("zip"));
				addresses.add(address);
            }
        } catch (SQLException e) {
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

        return addresses;
	}
}