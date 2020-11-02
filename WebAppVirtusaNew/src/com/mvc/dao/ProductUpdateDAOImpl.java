package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.ProductBean;
import com.mvc.util.DBConnection;

public class ProductUpdateDAOImpl implements ProductUpdateDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public boolean update(ProductBean pb) {
		boolean flag=false;
		try {
			String sql="UPDATE products SET category='"+pb.getCategory()+"', description='"+pb.getDescription()+"', count="+pb.getCount()+", cost="+pb.getCost()+", currency='"+pb.getCurrency()+"' where id="+pb.getId()+" and expirydate='"+pb.getExpirydate()+"'";
			con=DBConnection.createConnection();
			preparedStatement=con.prepareStatement(sql);
			preparedStatement.executeUpdate(sql);
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
