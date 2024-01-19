package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import dao.PoDAO;
import dao.PoDAOImpl;
import model.Catalog;
import model.Cart;
import model.Order;
import model.Identity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import model.Amount;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve banking details from the request
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");

        // Process the payment and update order status (implement as needed)

        HttpSession session = request.getSession(false);
        
        Cart c = (Cart) session.getAttribute("cart");
        Identity i = (Identity) session.getAttribute("identity");
        
        if (session != null && c != null && i != null)
        {
        	ItemDAO dao = new ItemDAOImpl(this.getServletContext());
        	
        	List<Amount> items = new ArrayList<Amount>();
        	
        	for (int j = 0 ; j < c.size(); j++)
        	{
        		items.add(new Amount(c.getCart().get(j).getId(), c.getCart().get(j).getQty()));
        	}
        	
        	LocalDate date = LocalDate.now();
        	  String text = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        	  LocalDate parsedDate = LocalDate.parse(text, DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        	
        	Order o = new Order(items, parsedDate.toString());
        	  
        	PoDAO poDao = new PoDAOImpl(this.getServletContext());
        	
        	poDao.insertPO(i.getId(), o);
        	
        	i.setOrders(poDao.getPOs(i.getId()));
        	
        	if (this.getServletContext().getAttribute("catalog") != null)
        	{
        		dao.decreaseQty(((Cart)session.getAttribute("cart")).getCart(), (Catalog) this.getServletContext().getAttribute("catalog"));
        	}
        	
        }
        
        // Redirect to a confirmation page
		request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        
        session.removeAttribute("cart");
        session.removeAttribute("total");
    }
}
