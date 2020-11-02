package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.UserBean;
import com.mvc.util.DBConnection;

public class UserUpdateDAOImpl implements UserUpdateDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public boolean update(UserBean ub) {
		boolean flag=false;
		try {
			String sql="UPDATE users SET firstname='"+ub.getFirstname()+"', lastname='"+ub.getLastname()+"', email='"+ub.getEmail()+"', password='"+ub.getPassword()+"' where username='"+ub.getUsername()+"'";
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
