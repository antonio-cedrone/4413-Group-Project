package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.Identity;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("identity") == null)
		{
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			
			if (password.equals(repassword))
			{
				UserDAO user = new UserDAOImpl(this.getServletContext());
				
				Identity i = user.getUser(username, password);
				
				if (i == null)
				{					
					user.newAccount(username, repassword, email);
					
					request.getRequestDispatcher("signin.jsp").forward(request, response);
				}
				else
				{
					response.setContentType("text/html");
					 PrintWriter pwriter = response.getWriter();
					 
					pwriter.print("<b><h2 style='text-align: center; color: white;'>User already exists</h2></b>");

					 RequestDispatcher dis=request.getRequestDispatcher("signup.jsp");
					 dis.include(request, response); 
				}
			}
			else
			{
				response.setContentType("text/html");
				 PrintWriter pwriter = response.getWriter();
				 
				pwriter.print("<b><h2 style='text-align: center; color: white;'>Passwords do not match!</h2></b>");

				 RequestDispatcher dis=request.getRequestDispatcher("signup.jsp");
				 dis.include(request, response); 
			}
		}
		else
		{
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
