package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.UserBean;
import com.mvc.dao.AddUserDAO;
import com.mvc.dao.AddUserDAOImpl;

/**
 * Servlet implementation class HomeAddController
 */
public class HomeAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	AddUserDAO addDAO = null;

	public HomeAddController() {
		addDAO = new AddUserDAOImpl();
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
		
		//Setting parameters and calling the function
		UserBean ub = new UserBean();
		ub.setFirstname(request.getParameter("firstName"));
		ub.setLastname(request.getParameter("lastName"));
		ub.setUsername(request.getParameter("uname"));
		ub.setEmail(request.getParameter("mail"));
		ub.setPassword(request.getParameter("pwd"));
		ub.setAccessright(request.getParameter("privilege"));

		if (addDAO.save(ub)) {
			request.setAttribute("message", "New user Added!!");
		} else {
			request.setAttribute("message", "failed to add user as username already exists!!");
		}

		dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);

	}

}
