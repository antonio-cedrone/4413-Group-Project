package dao;


import java.util.List;

import model.Identity;

public interface UserDAO {
	public Identity getUser(String username, String password);
	List<Identity> getAllAccounts();
    void updateAccount(Identity account);
    void deleteAccount(Identity account);
    public void newAccount(String user, String password, String email);
}
