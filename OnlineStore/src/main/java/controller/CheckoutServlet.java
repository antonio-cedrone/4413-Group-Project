package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Catalog;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the user to the checkout page
    	
    	HttpSession session = request.getSession();
    	
    	Cart c = (Cart) session.getAttribute("cart");
    	Catalog cat = (Catalog) this.getServletContext().getAttribute("catalog");
    	
    	if (c != null && cat != null)
    	{
    		for (int i = 0; i < c.size(); i++)
    		{
    			for (int j = 0; j < cat.size(); j++)
    			{
	    			if (c.getCart().get(i).getId() == cat.getCatalog().get(j).getId())
	    			{
	    				if (c.getCart().get(i).getQty() > cat.getCatalog().get(j).getQty())
	    				{
	    					session.setAttribute("total", Double.parseDouble(session.getAttribute("total").toString()) - c.getCart().get(i).getPrice()*c.getCart().get(i).getQty()); 
	    					c.remove(c.getCart().get(i).getId());
	    					break;
	    				}
	    			}
    			}
    		}
    	}
    	
    	if (c.size() > 0)
    	{
    		request.getRequestDispatcher("checkout.jsp").forward(request, response);
    	}
    	else
    	{
    		request.getRequestDispatcher("cart.jsp").forward(request, response);
    	}
    }
}
