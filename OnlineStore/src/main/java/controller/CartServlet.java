package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import model.Cart;
import model.Catalog;
import model.Item;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession().getAttribute("cart") == null) {
			Cart testCart = new Cart();
			/*testCart.add(new Item(1, "Name1", "cat", "brand", 50, "desc1", 5, ("blah").getBytes()));
			testCart.add(new Item(2, "Name2", "cat", "brand", 100, "desc1", 10, ("blah").getBytes()));
			testCart.add(new Item(3, "Name3", "cat", "brand", 200, "desc1", 15, ("blah").getBytes()));*/
			request.getSession().setAttribute("cart", testCart);
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(request.getParameter("todo") != null) {
			if(request.getParameter("todo").equals("Delete")) {
				cart.remove(Integer.parseInt(request.getParameter("id")));
			}else if(request.getParameter("todo").equals("Update")) {
				cart.updateQty(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("newQty")));
			}else if(request.getParameter("todo").equals("add") && request.getParameterValues("wishlist") != null) {
				String[] ids = request.getParameterValues("wishlist");
				
				ItemDAO dao = new ItemDAOImpl(request.getServletContext());
				for(String i : ids) {
					for(Item e : dao.getItems()) {
						if(e.getId() == Integer.parseInt(i)) {
							Item add = new Item(e.getId(), e.getName(), e.getCategory(), e.getBrand(), e.getPrice(), e.getDesc(), Integer.parseInt(request.getParameter("qty"+i)), e.getImage());
							cart.add(add);
							break;
						}
					}
				}
			}
		}
		List<Item> items = cart.getCart();
		double total = 0;
		for(Item e : items) {
			total += e.getQty() * e.getPrice();
		}
		request.getSession().setAttribute("total", total);
		request.getSession().setAttribute("cart", cart);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
