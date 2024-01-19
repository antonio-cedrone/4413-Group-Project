package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import model.Address;
import model.Identity;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("todo") != null && request.getParameter("todo").equals("update")) {
			Identity i = (Identity) request.getSession().getAttribute("identity");
			i.setFirstName(request.getParameter("firstname"));
			i.setLastName(request.getParameter("lastname"));
			Address address = new Address();
			address.setStreet(request.getParameter("address"));
			address.setCity(request.getParameter("city"));
			address.setZip(request.getParameter("postalCode"));
			address.setProvince(request.getParameter("province"));
			i.setAddress(address);
			i.setCreditCard(request.getParameter("creditCard"));
			i.setCvv(request.getParameter("cvv"));
			i.setExpiry(request.getParameter("expiryDate"));
			
			AddressDAO addressDAO = new AddressDAOImpl(this.getServletContext());
			UserDAO user = new UserDAOImpl(this.getServletContext());
		
			if (i.getAddressId() == 0)
			{
				i.setAddressId(addressDAO.newAddress(address.getStreet(), address.getProvince(), address.getCity(), address.getZip()));
			}
			else
			{
				addressDAO.updateAddress(i.getAddressId(), address.getStreet(), address.getProvince(), address.getCity(), address.getZip());
			}
			
			address.setId(i.getAddressId());
			
			user.updateAccount(i);
		}
		request.getRequestDispatcher("account.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
