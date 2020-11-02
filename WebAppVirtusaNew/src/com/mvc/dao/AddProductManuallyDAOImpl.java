package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.ProductBean;
import com.mvc.util.DBConnection;

public class AddProductManuallyDAOImpl implements AddProductManuallyDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public boolean save(ProductBean pb) {
		boolean flag=false;
		try {
			String sql1="INSERT INTO Warehouse(id,vendorcode,threshold) values ("+pb.getId()+", "+pb.getVendorcode()+", "+pb.getThreshold()+")";
			String sql2="INSERT INTO products(id,category,description,count,cost,currency,expirydate) values("+pb.getId()+", '"+pb.getCategory()+"', '"+pb.getDescription()+"', "+pb.getCount()+", "+pb.getCost()+", '"+pb.getCurrency()+"', '"+pb.getExpirydate()+"')";
			con=DBConnection.createConnection();
			preparedStatement=con.prepareStatement(sql1);
			preparedStatement.executeUpdate();
			preparedStatement=con.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
