package com.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.ProductBean;
import com.mvc.dao.ViewWarehouseProductsDAO;
import com.mvc.dao.ViewWarehouseProductsDAOImpl;

/**
 * Servlet implementation class WarehouseProductsViewController
 */
public class WarehouseProductsViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	ViewWarehouseProductsDAO viewDAO = null;

	public WarehouseProductsViewController() {
		viewDAO = new ViewWarehouseProductsDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		}

		List<ProductBean> al = viewDAO.get();
		List<ProductBean> alu = getDistinct(al);
		request.setAttribute("al", al);
		request.setAttribute("alu", alu);
		dispatcher = request.getRequestDispatcher("warehouse.jsp");
		dispatcher.forward(request, response);

	}

	private List<ProductBean> getDistinct(List<ProductBean> al) {

		List<ProductBean> sol = new ArrayList<>();
		for (int i = 0; i < al.size(); i++) {
			ProductBean pb = new ProductBean();
			pb.setId(al.get(i).getId());
			pb.setThreshold(al.get(i).getThreshold());
			boolean test = false;
			for (int j = 0; j < sol.size(); j++) {
				ProductBean pb1 = sol.get(j);
				if (pb1.getId() == pb.getId()) {
					test = true;
					break;
				}
			}
			if (test == false) {
				sol.add(pb);
			}
		}
		return sol;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
