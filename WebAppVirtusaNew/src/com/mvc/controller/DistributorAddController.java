package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.DistributorBean;
import com.mvc.dao.AddDistributorDAO;
import com.mvc.dao.AddDistributorDAOImpl;

/**
 * Servlet implementation class DistributorAddController
 */
public class DistributorAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	AddDistributorDAO addDAO = null;

	public DistributorAddController() {
		addDAO = new AddDistributorDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}

		DistributorBean db = new DistributorBean();

		// Setting parameters and calling the function
		db.setFirstname(request.getParameter("firstName"));
		db.setLastname(request.getParameter("lastName"));
		db.setCompanyname(request.getParameter("cname"));
		db.setAddress(request.getParameter("address"));
		db.setEmail(request.getParameter("cmail"));
		db.setPhoneno(request.getParameter("cphone"));

		if (addDAO.save(db)) {
			request.setAttribute("message", "New distributor Added!!");
		} else {
			request.setAttribute("message", "failed to add distributor as distributor already exists!!");
		}

		dispatcher = request.getRequestDispatcher("distributors.jsp");
		dispatcher.forward(request, response);

	}

}
