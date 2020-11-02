package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.LocationBean;
import com.mvc.util.DBConnection;

public class GetLocationDAOImpl implements GetLocationDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public List<LocationBean> get() {
		
		List<LocationBean> list=null;
		LocationBean lb=null;
		
		try {
			list=new ArrayList<LocationBean>();
			String sql="SELECT * from shop";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				lb=new LocationBean();
				lb.setLocation(resultSet.getString("location"));
				lb.setUsername(resultSet.getString("username"));
				list.add(lb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

}
