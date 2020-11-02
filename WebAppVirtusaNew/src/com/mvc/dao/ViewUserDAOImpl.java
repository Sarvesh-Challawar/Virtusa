package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.UserBean;
import com.mvc.util.DBConnection;

public class ViewUserDAOImpl implements ViewUserDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public List<UserBean> get() {
		
		List<UserBean> list=null;
		UserBean ub=null;
		
		try {
			list=new ArrayList<UserBean>();
			String sql="select u.firstname,u.lastname,u.username,u.email,u.password,ur.accessright from users u,userrights ur where u.username=ur.username";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				ub=new UserBean();
				ub.setFirstname(resultSet.getString("firstname"));
				ub.setLastname(resultSet.getString("lastname"));
				ub.setUsername(resultSet.getString("username"));
				ub.setEmail(resultSet.getString("email"));
				ub.setAccessright(resultSet.getString("accessright"));
				list.add(ub);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
