package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.ProductBean;
import com.mvc.dao.ThresholdUpdateDAO;
import com.mvc.dao.ThresholdUpdateDAOImpl;

/**
 * Servlet implementation class EditThresholdController
 */
public class EditThresholdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ThresholdUpdateDAO updateDAO = null;

	public EditThresholdController() {
		updateDAO = new ThresholdUpdateDAOImpl();
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
		
		//Setting the parameters and calling a function
		pb.setId(Integer.parseInt(request.getParameter("pid")));
		pb.setThreshold(Integer.parseInt(request.getParameter("threshold")));

		if (updateDAO.update(pb)) {
			request.setAttribute("message3", "Threshold Value updated!!");
		} else {
			request.setAttribute("message3", "failed to update threshold values!!");
		}

		dispatcher = request.getRequestDispatcher("warehouse.jsp");
		dispatcher.forward(request, response);
	}

}
