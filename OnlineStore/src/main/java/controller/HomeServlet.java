package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Catalog;
import dao.*;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ItemDAO dao = new ItemDAOImpl(request.getServletContext());
		
		if(request.getSession(true).getAttribute("sessionCatalog") == null) {
			request.getSession(true).setAttribute("sessionCatalog", request.getServletContext().getAttribute("catalog"));
		}
		
		Catalog catalog = (Catalog) request.getSession().getAttribute("sessionCatalog");
		String todo;
		if(request.getParameter("todo") != null) {
			todo = request.getParameter("todo");
		}else {
			todo = "reset";
		}
			
		if(todo.equals("sort")) {
			if(request.getParameter("sort").equals("name")) {
				catalog.sortByName();
			}else if(request.getParameter("sort").equals("expen")){
				catalog.sortMostExpensive();
			}else {
				catalog.sortLeastExpensive();
			}
		}else if(todo.equals("cfil")) {
			catalog = new Catalog(dao.getItemsByCat(request.getParameter("catfilter")));
			
		}else if(todo.equals("bfil")) {
			catalog = new Catalog(dao.getItemsByBrand(request.getParameter("brandfilter")));
			
		}else {
			catalog = new Catalog(dao.getItems());
		}
		
		
		
		
			request.getSession(true).setAttribute("sessionCatalog", catalog);
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
