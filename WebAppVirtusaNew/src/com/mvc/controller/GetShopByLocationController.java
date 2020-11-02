package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.ShopProductBean;
import com.mvc.dao.ShopViewGetByLocationDAO;
import com.mvc.dao.ShopViewGetByLocationDAOImpl;

/**
 * Servlet implementation class GetShopByLocationController
 */
public class GetShopByLocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ShopViewGetByLocationDAO viewlocDAO = null;

	public GetShopByLocationController() {
		viewlocDAO = new ShopViewGetByLocationDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}

		//Passing the location to fetch details from database
		String location = request.getParameter("location");
		List<ShopProductBean> al = viewlocDAO.get(location);
		request.setAttribute("albyloc", al);

		dispatcher = request.getRequestDispatcher("shelf.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
