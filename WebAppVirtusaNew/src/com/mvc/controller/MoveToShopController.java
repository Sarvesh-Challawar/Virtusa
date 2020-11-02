package com.mvc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.MoveBean;
import com.mvc.dao.MoveProductDAO;
import com.mvc.dao.MoveProductDAOImpl;

/**
 * Servlet implementation class MoveToShopController
 */
public class MoveToShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	MoveProductDAO moveDAO = null;

	public MoveToShopController() {
		moveDAO = new MoveProductDAOImpl();
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

		MoveBean mb = new MoveBean();
		String location = (String) request.getParameter("location");
		int id = Integer.parseInt(request.getParameter("pid"));
		int movecount = Integer.parseInt(request.getParameter("movecount"));
		String dt = (String) request.getParameter("expirydate");
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date mySqlDate = new java.sql.Date(date.getTime());

		mb.setLocation(location);
		mb.setId(id);
		mb.setMovecount(movecount);
		mb.setExpirydate(mySqlDate);

		String notif = moveDAO.move(mb);
		if (!notif.equals("")) {
			//This is actually a notification
			request.setAttribute("notif", "Low stocks for products with id's [" + notif + "].");
			request.setAttribute("movemessage", "Products have been moved to shop with location" + location);
		} else {
			request.setAttribute("movemessage", "Failed to move products to location" + location);
		}

		dispatcher = request.getRequestDispatcher("warehouse.jsp");
		dispatcher.forward(request, response);

	}

}
