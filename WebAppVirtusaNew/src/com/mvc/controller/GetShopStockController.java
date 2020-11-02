package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.StockBean;
import com.mvc.dao.GetStockDAO;
import com.mvc.dao.GetStockDAOImpl;

/**
 * Servlet implementation class GetShopStockController
 */
public class GetShopStockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	GetStockDAO getDAO = null;

	public GetShopStockController() {
		getDAO = new GetStockDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}
		
		//Calling method to store stock information in a list
		List<StockBean> al = getDAO.get();
		request.setAttribute("alstock", al);
		dispatcher = request.getRequestDispatcher("shelf.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
