package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.ProductBean;
import com.mvc.util.DBConnection;

public class ExpirydateDAOImpl implements ExpirydateDAO {

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
			String sql="SELECT id,category,description,count,expirydate from products where DATEDIFF(expirydate,now())<3";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				pb=new ProductBean();
				pb.setId(resultSet.getInt("id"));
				pb.setCategory(resultSet.getString("category"));
				pb.setDescription(resultSet.getString("description"));
				pb.setCount(resultSet.getInt("count"));
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
