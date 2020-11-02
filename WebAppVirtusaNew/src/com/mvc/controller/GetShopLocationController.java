package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.LocationBean;
import com.mvc.dao.GetLocationDAO;
import com.mvc.dao.GetLocationDAOImpl;

/**
 * Servlet implementation class GetShopLocationController
 */
public class GetShopLocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	GetLocationDAO getDAO = null;

	public GetShopLocationController() {
		getDAO = new GetLocationDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}

		//Storing all the location in a list to display it in jsp page
		List<LocationBean> al = getDAO.get();
		request.setAttribute("al", al);
		dispatcher = request.getRequestDispatcher("shelf.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
