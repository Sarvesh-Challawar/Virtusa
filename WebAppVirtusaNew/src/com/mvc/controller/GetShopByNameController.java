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
import com.mvc.dao.ShopViewGetByNameDAO;
import com.mvc.dao.ShopViewGetByNameDAOImpl;

/**
 * Servlet implementation class GetShopByNameController
 */
public class GetShopByNameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ShopViewGetByNameDAO viewnameDAO = null;

	public GetShopByNameController() {
		viewnameDAO = new ShopViewGetByNameDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}

		//Passing the name to the method fetch details from database
		String name = request.getParameter("pname");
		List<ShopProductBean> al = viewnameDAO.get(name);
		request.setAttribute("albyname", al);

		dispatcher = request.getRequestDispatcher("shelf.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
