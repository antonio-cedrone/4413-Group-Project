package controller;

import java.sql.Connection;
import dao.*;
import model.Catalog;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.sqlite.SQLiteDataSource;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         //On startup, a list of items (inventory) and a list of identities (identity list) is initialized. Initial quantity for all items is 100
    	//I decided to separate inventory and database so that the remaining quantities do not need to be updated in the database.
    	SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:"+sce.getServletContext().getRealPath("/Inventory.db"));
        sce.getServletContext().setAttribute("source", ds);

    	
    	ItemDAO dao = new ItemDAOImpl(sce.getServletContext());
    	
    	sce.getServletContext().setAttribute("catalog", new Catalog(dao.getItems()));
    }
	
}