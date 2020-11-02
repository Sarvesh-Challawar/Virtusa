package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.UserBean;
import com.mvc.util.DBConnection;

public class AddUserDAOImpl implements AddUserDAO {
	
	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;

	@Override
	public boolean save(UserBean ub) {
		boolean flag=false;
		try {
			String sql="INSERT INTO users(firstname,lastname,username,email,password)values ('"+ub.getFirstname()+"', '"+ub.getLastname()+"', '"+ub.getUsername()+"', '"+ub.getEmail()+"', '"+ub.getPassword()+"')";
			String sql1="INSERT INTO userrights(username,accessright)values ('"+ub.getUsername()+"', '"+ub.getAccessright()+"')";
			con=DBConnection.createConnection();
			preparedStatement=con.prepareStatement(sql);
			preparedStatement.executeUpdate();
			preparedStatement=con.prepareStatement(sql1);
			preparedStatement.executeUpdate();
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
