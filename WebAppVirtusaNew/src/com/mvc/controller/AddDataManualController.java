package com.mvc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.ProductBean;
import com.mvc.dao.AddProductManuallyDAO;
import com.mvc.dao.AddProductManuallyDAOImpl;

/**
 * Servlet implementation class AddDataManualController
 */
public class AddDataManualController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RequestDispatcher dispatcher=null;
	AddProductManuallyDAO addDAO=null;
	
    public AddDataManualController() {
        addDAO=new AddProductManuallyDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Checking if user is logged in or not
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("login.jsp");
		}
		
		ProductBean pb=new ProductBean();
		
		//converting a date in form of string to Date object and then to sql Date object
		String dt=request.getParameter("expirydate").toString();
		java.util.Date date=null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date mySqlDate = new java.sql.Date(date.getTime());
		
		//setting parameters and calling the function to add the data to database
		pb.setId(Integer.parseInt(request.getParameter("pid")));
		pb.setVendorcode(Integer.parseInt(request.getParameter("vendorcode")));
		pb.setCategory(request.getParameter("category"));
		pb.setDescription(request.getParameter("description"));
		pb.setCount(Integer.parseInt(request.getParameter("count")));
		pb.setCost(Double.parseDouble(request.getParameter("cost")));
		pb.setCurrency(request.getParameter("currency"));
		pb.setExpirydate(mySqlDate);
		pb.setThreshold(Integer.parseInt(request.getParameter("threshold")));
		if(addDAO.save(pb)) {
			request.setAttribute("message1", "New product Added!!");
		}else {
			request.setAttribute("message1", "failed to add product!!");
		}
		
		dispatcher=request.getRequestDispatcher("warehouse.jsp");
		dispatcher.forward(request, response);
		
	}

}
