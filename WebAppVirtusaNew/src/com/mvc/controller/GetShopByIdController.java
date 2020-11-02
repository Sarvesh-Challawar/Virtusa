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
import com.mvc.dao.ShopViewGetByIDDAO;
import com.mvc.dao.ShopViewGetByIDDAOImpl;

/**
 * Servlet implementation class GetShopByIdController
 */
public class GetShopByIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ShopViewGetByIDDAO viewIDDAO = null;

	public GetShopByIdController() {
		viewIDDAO = new ShopViewGetByIDDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}
		
		//Passing id to the method to get database information
		int id = Integer.parseInt(request.getParameter("pid"));
		List<ShopProductBean> al = viewIDDAO.get(id);
		request.setAttribute("albyid", al);

		dispatcher = request.getRequestDispatcher("shelf.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
