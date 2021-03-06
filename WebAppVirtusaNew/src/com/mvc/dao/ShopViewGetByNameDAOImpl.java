package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.ShopProductBean;
import com.mvc.util.DBConnection;

public class ShopViewGetByNameDAOImpl implements ShopViewGetByNameDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public List<ShopProductBean> get(String name) {

		List<ShopProductBean> list=null;
		ShopProductBean sb=null;
		try {
			list=new ArrayList<ShopProductBean>();
			String sql="SELECT * FROM shopproducts where description='"+name+"'";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				sb=new ShopProductBean();
				sb.setLocation(resultSet.getString("location"));
				sb.setId(resultSet.getInt("id"));
				sb.setCategory(resultSet.getString("category"));
				sb.setDescription(name);
				sb.setCount(resultSet.getInt("count"));
				sb.setCost(resultSet.getDouble("cost"));
				sb.setCurrency(resultSet.getString("currency"));
				java.sql.Date date=resultSet.getDate("expirydate");
				java.util.Date d=new java.util.Date(date.getTime());
				sb.setExpirydate(d);
				list.add(sb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
