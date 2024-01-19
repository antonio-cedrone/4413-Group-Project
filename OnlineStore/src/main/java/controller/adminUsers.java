package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.Identity;

/**
 * Servlet implementation class adminUsers
 */
@WebServlet("/adminUsers")
public class adminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserDAO user = new UserDAOImpl(this.getServletContext());
        List<Identity>users = user.getAllAccounts();

        // Convert the list of users to JSON manually
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++) {
            Identity user1 = users.get(i);
            jsonBuilder.append("{\"username\":\"").append(user1.getUser()).append("\",\"email\":\"").append(user1.getEmail()).append("\"}");

            if (i < users.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");

        // Send the JSON response
        response.setContentType("application/json");
        response.getWriter().write(jsonBuilder.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
