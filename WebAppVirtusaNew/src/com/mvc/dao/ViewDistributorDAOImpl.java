package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.DistributorBean;
import com.mvc.util.DBConnection;

public class ViewDistributorDAOImpl implements ViewDistributorDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public List<DistributorBean> get() {
		
		List<DistributorBean> list=null;
		DistributorBean db=null;
		
		try {
			list=new ArrayList<DistributorBean>();
			String sql="SELECT * FROM distributor";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				db=new DistributorBean();
				db.setVendorcode(resultSet.getInt("vendorcode"));
				db.setFirstname(resultSet.getString("firstname"));
				db.setLastname(resultSet.getString("lastname"));
				db.setCompanyname(resultSet.getString("companyname"));
				db.setAddress(resultSet.getString("address"));
				db.setEmail(resultSet.getString("email"));
				db.setPhoneno(resultSet.getString("phoneno"));
				list.add(db);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
