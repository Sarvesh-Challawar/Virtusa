package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.ProductBean;
import com.mvc.dao.ExpirydateDAO;
import com.mvc.dao.ExpirydateDAOImpl;

/**
 * Servlet implementation class ExpiryNotificationController
 */
public class ExpiryNotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ExpirydateDAO eDAO = null;

	public ExpiryNotificationController() {
		eDAO = new ExpirydateDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}
		
		List<ProductBean> al1=eDAO.get();
		request.setAttribute("al1", al1);
		dispatcher=request.getRequestDispatcher("notifications.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
