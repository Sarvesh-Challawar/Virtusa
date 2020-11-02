package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.DistributorBean;
import com.mvc.dao.ViewDistributorDAO;
import com.mvc.dao.ViewDistributorDAOImpl;

/**
 * Servlet implementation class DistributorViewController
 */
public class DistributorViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	RequestDispatcher dispatcher=null;
	ViewDistributorDAO viewDAO=null;
	
    public DistributorViewController() {
        viewDAO=new ViewDistributorDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Checking if user is logged in or not
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("login.jsp");
		}
		
		//Storing the details of distributors in the form of a list
		List<DistributorBean> al=viewDAO.get();
		request.setAttribute("al", al);
		dispatcher=request.getRequestDispatcher("distributors.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
