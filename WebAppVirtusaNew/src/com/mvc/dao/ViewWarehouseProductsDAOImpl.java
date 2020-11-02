package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.ProductBean;
import com.mvc.util.DBConnection;

public class ViewWarehouseProductsDAOImpl implements ViewWarehouseProductsDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public List<ProductBean> get() {
		List<ProductBean> list=null;
		ProductBean pb=null;
		
		try {
			list=new ArrayList<ProductBean>();
			String sql="SELECT w.id,w.vendorcode,w.threshold,p.category,p.description,p.count,p.cost,p.currency,p.expirydate FROM Warehouse w,products p where w.id=p.id ORDER BY p.expirydate	";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				pb=new ProductBean();
				pb.setId(resultSet.getInt("id"));
				pb.setVendorcode(resultSet.getInt("vendorcode"));
				pb.setThreshold(resultSet.getInt("threshold"));
				pb.setCategory(resultSet.getString("category"));
				pb.setDescription(resultSet.getString("description"));
				pb.setCount(resultSet.getInt("count"));
				pb.setCost(resultSet.getDouble("cost"));
				pb.setCurrency(resultSet.getString("currency"));
				java.sql.Date date=resultSet.getDate("expirydate");
				java.util.Date d=new java.util.Date(date.getTime());
				pb.setExpirydate(d);
				list.add(pb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
