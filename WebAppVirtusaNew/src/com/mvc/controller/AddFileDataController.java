package com.mvc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import com.mvc.dao.AddFileDataDAO;
import com.mvc.dao.AddFileDataDAOImpl;

/**
 * Servlet implementation class AddFileDataController
 */
public class AddFileDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	AddFileDataDAO addDAO = null;

	public AddFileDataController() {
		addDAO = new AddFileDataDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}

		// Getting the path to read the file from the user
		String path = request.getParameter("path");
		SimpleDateFormat df = null;
		File f = new File(path);
		if (f.exists()) {
			// Getting and reading the file
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int failed = 0;
			ProductBean pb = null;
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(",");

				// converting expiry date to Date object in java and then to SQL date object
				java.util.Date date = null;
				try {
					date = new SimpleDateFormat("dd-MM-yyyy").parse(arr[7].toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.sql.Date mySqlDate = new java.sql.Date(date.getTime());

				// setting parameters and calling the function to add to database
				pb = new ProductBean();
				pb.setId(Integer.parseInt(arr[0]));
				pb.setVendorcode(Integer.parseInt(arr[1]));
				pb.setThreshold(5);
				pb.setCategory(arr[2]);
				pb.setDescription(arr[3]);
				pb.setCount(Integer.parseInt(arr[4]));
				pb.setCost(Double.parseDouble(arr[5]));
				pb.setCurrency(arr[6]);
				pb.setExpirydate(mySqlDate);

				if (addDAO.save(pb)) {

				} else {
					failed += 1;
				}
			}
			if (failed == 0) {
				request.setAttribute("message", "Added all products from file!!");
			} else {
				request.setAttribute("message", "Failed to add " + failed + " products!!");
			}
		} else {
			request.setAttribute("message", "File not found at the given Location!!");
		}

		dispatcher = request.getRequestDispatcher("warehouse.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
