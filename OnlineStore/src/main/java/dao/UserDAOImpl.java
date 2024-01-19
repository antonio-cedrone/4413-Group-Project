package dao;

import javax.servlet.ServletContext;
import org.sqlite.SQLiteDataSource;


import model.Identity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO{
	ServletContext cont;
	public UserDAOImpl(ServletContext cont) {
		this.cont = cont;
	}
	@Override
	public Identity getUser(String username, String password) {
		Identity user = null;
		
		Connection con = null;
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			
			PreparedStatement statement = con.prepareStatement("select * from customer where username = ? and password = ?");
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet resultSet =  statement.executeQuery();
						
			//there will only ever be one due to database constraints
			//but if there is no returned user you get a null user as expected
			while (resultSet.next())
			{
				user = new Identity();
				user.setId(resultSet.getInt("id"));
				user.setAddressId(resultSet.getInt("addressId"));
				user.setUser(resultSet.getString("username"));
				user.setPass(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setPhone(resultSet.getString("phone"));
				user.setEmail(resultSet.getString("email"));
				user.setCreditCard(resultSet.getString("creditcard"));
				user.setCvv(resultSet.getString("cvv"));
				user.setExpiry(resultSet.getString("expiry"));
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
		
		return user;
	}
	
	public List<Identity> getAllAccounts() {
        List<Identity> accounts = new ArrayList<>();        
        Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("select * from customer");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Identity user = new Identity();
                user.setId(resultSet.getInt("id"));
                user.setAddressId(resultSet.getInt("addressID"));
                user.setUser(resultSet.getString("username"));
                user.setPass(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setCreditCard(resultSet.getString("creditcard"));
				user.setCvv(resultSet.getString("cvv"));
				user.setExpiry(resultSet.getString("expiry"));
                accounts.add(user);
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

        return accounts;
    }
	
	public void updateAccount(Identity account) {
		Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("update customer set addressId = ?, username = ?, firstName = ?, lastName = ?, phone = ?, creditcard = ?, cvv=?, expiry=?, password=? where id = ?");
            statement.setInt(1, account.getAddressId());
            statement.setString(2, account.getUser());
            statement.setString(3, account.getFirstName());
            statement.setString(4, account.getLastName());
            statement.setString(5, account.getPhone());
            statement.setString(6, account.getCreditCard());
            statement.setString(7, account.getCvv());
            statement.setString(8, account.getExpiry());
            statement.setString(9, account.getPass());
            statement.setInt(10, account.getId());
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
	
	
    public void deleteAccount(Identity account) {
    	Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("delete from customer where id = ?");
            statement.setInt(1, account.getId());
            statement.executeUpdate();
            con.commit();
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
    
    public void newAccount(String user, String password, String email) {
    	Connection con = null;
		
		try {
			con = ((SQLiteDataSource) cont.getAttribute("source")).getConnection();
			PreparedStatement statement = con.prepareStatement("insert into Customer(username, password, email) values (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.execute();
			
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
	
}