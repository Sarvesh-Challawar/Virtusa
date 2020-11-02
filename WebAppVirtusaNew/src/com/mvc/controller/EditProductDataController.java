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

import com.mvc.bean.ProductBean;
import com.mvc.dao.ProductUpdateDAO;
import com.mvc.dao.ProductUpdateDAOImpl;

/**
 * Servlet implementation class EditProductDataController
 */
public class EditProductDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ProductUpdateDAO updateDAO = null;

	public EditProductDataController() {
		updateDAO = new ProductUpdateDAOImpl();
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

		ProductBean pb = new ProductBean();
		
		//Converting string date to Date in java and then to SQL Date
		String dt = request.getParameter("expirydate").toString();
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date mySqlDate = new java.sql.Date(date.getTime());
		
		//Setting parameters and calling the function
		pb.setId(Integer.parseInt(request.getParameter("pid")));
		pb.setVendorcode(Integer.parseInt(request.getParameter("vendorcode")));
		pb.setCategory(request.getParameter("category"));
		pb.setDescription(request.getParameter("description"));
		pb.setCount(Integer.parseInt(request.getParameter("count")));
		pb.setCost(Double.parseDouble(request.getParameter("cost")));
		pb.setCurrency(request.getParameter("currency"));
		pb.setExpirydate(mySqlDate);

		if (updateDAO.update(pb)) {
			request.setAttribute("message2", "Product details updated!!");
		} else {
			request.setAttribute("message2", "failed to update product details!!");
		}

		dispatcher = request.getRequestDispatcher("warehouse.jsp");
		dispatcher.forward(request, response);

	}

}
