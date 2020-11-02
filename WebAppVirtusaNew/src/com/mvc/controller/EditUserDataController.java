package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.UserBean;
import com.mvc.dao.UserUpdateDAO;
import com.mvc.dao.UserUpdateDAOImpl;

/**
 * Servlet implementation class EditUserDataController
 */
public class EditUserDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	UserUpdateDAO updateDAO = null;

	public EditUserDataController() {
		updateDAO = new UserUpdateDAOImpl();
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

		//Setting the parameters and calling the function
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String uname = request.getParameter("uname");
		String email = request.getParameter("mail");
		String password = request.getParameter("pwd");

		UserBean ub = new UserBean();
		ub.setFirstname(firstName);
		ub.setLastname(lastName);
		ub.setUsername(uname);
		ub.setEmail(email);
		ub.setPassword(password);

		if (updateDAO.update(ub)) {
			request.setAttribute("msg", "Sucessfull updated your data.");
		} else {
			request.setAttribute("msg", "Couln't update data!!");
		}

		dispatcher = request.getRequestDispatcher("settings.jsp");
		dispatcher.forward(request, response);

	}

}
