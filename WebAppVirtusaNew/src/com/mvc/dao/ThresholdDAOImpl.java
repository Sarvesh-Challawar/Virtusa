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

public class ThresholdDAOImpl implements ThresholdDAO {

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
			String sql="SELECT p.id,w.threshold,p.category,p.description,p.count from Warehouse w,products p where w.id=p.id and p.count<w.threshold";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next())
			{
				pb=new ProductBean();
				pb.setId(resultSet.getInt("id"));
				pb.setThreshold(resultSet.getInt("threshold"));
				pb.setCategory(resultSet.getString("category"));
				pb.setDescription(resultSet.getString("description"));
				pb.setCount(resultSet.getInt("count"));
				list.add(pb);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
		
	}

}
