package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.ProductBean;
import com.mvc.util.DBConnection;

public class AddFileDataDAOImpl implements AddFileDataDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public boolean save(ProductBean pb) {
		boolean flag=false;
		try {
			con=DBConnection.createConnection();
			String check="SELECT * from warehouse where id="+pb.getId();
			statement = con.createStatement(); //Statement is used to write queries. Read more about it.
            resultSet = statement.executeQuery(check);
            if(resultSet.next())
            {
            	/*String sql1="SELECT count,cost from products where id="+pb.getId();
            	resultSet=statement.executeQuery(sql1);
            	if(resultSet.next())
            	{
            		int countDB=resultSet.getInt("count");
            		double costDB=resultSet.getDouble("cost");
            		int countnew=countDB+pb.getCount();
            		double costnew=(costDB+pb.getCost())/2;
            		java.sql.Date date=new java.sql.Date(pb.getExpirydate().getTime());
            		String sql2="UPDATE products SET count="+countnew+", cost="+costnew+", expirydate='"+date+"' where id="+pb.getId();
            		preparedStatement=con.prepareStatement(sql2);
        			preparedStatement.executeUpdate();
            	}*/
            	java.sql.Date date=new java.sql.Date(pb.getExpirydate().getTime());
    			String sql1="INSERT INTO products(id,category,description,count,cost,currency,expirydate) values("+pb.getId()+", '"+pb.getCategory()+"', '"+pb.getDescription()+"', "+pb.getCount()+", "+pb.getCost()+", '"+pb.getCurrency()+"', '"+date+"')";
    			preparedStatement=con.prepareStatement(sql1);
    			preparedStatement.executeUpdate();
            }
            else
            {
            	String sql1="INSERT INTO Warehouse(id,vendorcode,threshold) values ("+pb.getId()+", "+pb.getVendorcode()+", "+pb.getThreshold()+")";
    			java.sql.Date date=new java.sql.Date(pb.getExpirydate().getTime());
    			String sql2="INSERT INTO products(id,category,description,count,cost,currency,expirydate) values("+pb.getId()+", '"+pb.getCategory()+"', '"+pb.getDescription()+"', "+pb.getCount()+", "+pb.getCost()+", '"+pb.getCurrency()+"', '"+date+"')";
    			preparedStatement=con.prepareStatement(sql1);
    			preparedStatement.executeUpdate();
    			preparedStatement=con.prepareStatement(sql2);
    			preparedStatement.executeUpdate();
            }
			
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
