package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.DistributorBean;
import com.mvc.util.DBConnection;

public class AddDistributorDAOImpl implements AddDistributorDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public boolean save(DistributorBean db) {
		boolean flag=false;
		try {
			String sql="INSERT INTO distributor(firstname,lastname,companyname,address,email,phoneno) values ('"+db.getFirstname()+"', '"+db.getLastname()+"', '"+db.getCompanyname()+"', '"+db.getAddress()+"', '"+db.getEmail()+"', '"+db.getPhoneno()+"')";
			con=DBConnection.createConnection();
			preparedStatement=con.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
