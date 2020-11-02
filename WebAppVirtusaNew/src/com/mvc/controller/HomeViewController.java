package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.UserBean;
import com.mvc.dao.ViewUserDAO;
import com.mvc.dao.ViewUserDAOImpl;

/**
 * Servlet implementation class HomeViewController
 */
public class HomeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ViewUserDAO viewDAO = null;

	public HomeViewController() {
		viewDAO = new ViewUserDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}
		
		//Calling a method to get user details
		List<UserBean> al = viewDAO.get();
		request.setAttribute("al", al);
		dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
