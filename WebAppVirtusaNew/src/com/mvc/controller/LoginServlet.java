package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.LoginBean;
import com.mvc.dao.LoginDao;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Checking if user is logged in or not
		
//		if (session != null) {
//			RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
//			dispatcher.forward(request, response);
//		}
//		else {
//			session = request.getSession();
//		}

		String userName = request.getParameter("uname");
		String password = request.getParameter("pass");

		LoginBean lb = new LoginBean();

		lb.setUserName(userName); 
		lb.setPassword(password);

		LoginDao loginDao = new LoginDao(); 

		String userValidate = loginDao.Authenticate(lb); 
		if (!userValidate.equals("Invalid user credentials")) {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", userName);
			session.setAttribute("rights", userValidate);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errMessage", userValidate); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}