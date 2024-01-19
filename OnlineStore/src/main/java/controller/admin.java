package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import dao.ItemDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Catalog;
import model.Identity;
import model.Item;
import model.Order;

/**
 * Servlet implementation class admin
 */
@WebServlet("/AdminServlet")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("todo") != null) {
			if(request.getParameter("todo").equals("inventory")) {
				ItemDAO dao = new ItemDAOImpl(this.getServletContext());
				List<Item> items = dao.getItems();
				request.getSession(true).setAttribute("displayItems", items);
				request.getSession(true).setAttribute("display", "inventory");
			}else if(request.getParameter("todo").equals("update")) {
				ItemDAO dao = new ItemDAOImpl(this.getServletContext());
				dao.updateQty(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("newQty")));
				request.getServletContext().setAttribute("catalog", new Catalog(dao.getItems()));
				request.getSession().setAttribute("displayItems", dao.getItems());
				request.getSession().setAttribute("display", "inventory");
			}else if(request.getParameter("todo").equals("history")) {
				PoDAO dao = new PoDAOImpl(this.getServletContext());
				List<Order> orders = dao.getAllOrders();
				request.getSession().setAttribute("orders", orders);
				request.getSession().setAttribute("display", "orders");
			}
			else if(request.getParameter("todo").equals("accounts")) {
				
			}
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
