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
import com.mvc.dao.ViewCurrentUserDAO;
import com.mvc.dao.ViewCurrentUserDAOImpl;

/**
 * Servlet implementation class SettingsViewController
 */
public class SettingsViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ViewCurrentUserDAO viewcDAO = null;

	public SettingsViewController() {
		viewcDAO = new ViewCurrentUserDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}

		String uname = (String) request.getSession(false).getAttribute("currentUser");
		List<UserBean> al = viewcDAO.get(uname);
		request.setAttribute("al", al);

		dispatcher = request.getRequestDispatcher("settings.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
