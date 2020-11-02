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

import com.mvc.bean.DamagedBean;
import com.mvc.dao.DamagedDAO;
import com.mvc.dao.DamagedDAOImpl;

/**
 * Servlet implementation class DamagedController
 */
public class DamagedController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher=null;
	DamagedDAO damDAO=null;
	
	public DamagedController() {
		damDAO=new DamagedDAOImpl();
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
		DamagedBean db=new DamagedBean();
		db.setId(Integer.parseInt(request.getParameter("pid")));
		db.setCount(Integer.parseInt(request.getParameter("movecount")));
		String dt = (String) request.getParameter("expirydate");
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date mySqlDate = new java.sql.Date(date.getTime());
		db.setExpirydate(mySqlDate);
		db.setReason(request.getParameter("reason"));
		
		if(damDAO.move(db))
		{
			request.setAttribute("dammsg", "Changes made");
		}
		else
		{
			request.setAttribute("dammsg", "Changes failed");
		}
		
		dispatcher = request.getRequestDispatcher("warehouse.jsp");
		dispatcher.forward(request, response);

	}

}
