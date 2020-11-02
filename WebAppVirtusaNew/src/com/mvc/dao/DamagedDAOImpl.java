package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.DamagedBean;
import com.mvc.util.DBConnection;

public class DamagedDAOImpl implements DamagedDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public boolean move(DamagedBean db) {

		boolean flag=false;
		try {
			con=DBConnection.createConnection();
			int countDB=0;
			String sql="SELECT count from products where id="+db.getId()+" and expirydate='"+db.getExpirydate()+"'";
			statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
            	countDB=resultSet.getInt("count");
            }
			String check="SELECT * from damaged where id="+db.getId()+" and expirydate='"+db.getExpirydate()+"' and reason='"+db.getReason()+"'";
			statement = con.createStatement();
            resultSet = statement.executeQuery(check);
            if(resultSet.next()) {
            	int oldcount=resultSet.getInt("count");
            	int newcount=db.getCount()+oldcount;
            	String sql1="UPDATE damaged SET count="+newcount+" where id="+db.getId()+" and expirydate='"+db.getExpirydate()+"' and reason='"+db.getReason()+"'";
            	preparedStatement=con.prepareStatement(sql1);
    			preparedStatement.executeUpdate();
            	int leftcount=countDB-db.getCount();
            	System.out.println(leftcount);
            	if(leftcount==0) {
            		String sql2="DELETE from products where id="+db.getId()+" and expirydate='"+db.getExpirydate()+"'";
            		preparedStatement=con.prepareStatement(sql2);
        			preparedStatement.executeUpdate();
            	}else {
            		//update products
            		String sql2="UPDATE products SET count="+leftcount+" where id="+db.getId()+" and expirydate='"+db.getExpirydate()+"'";
            		preparedStatement=con.prepareStatement(sql2);
        			preparedStatement.executeUpdate();
            	}
            }else {
            	int id=db.getId();
            	int damagecount=db.getCount();
            	int leftcount=countDB-damagecount;
            	String reason=db.getReason();
                String sql2="INSERT INTO damaged(id,count,expirydate,reason) values("+id+", "+damagecount+", '"+db.getExpirydate()+"', '"+reason+"')";
                preparedStatement=con.prepareStatement(sql2);
    			preparedStatement.executeUpdate();
    			String sql3="UPDATE products SET count="+leftcount+" where id="+db.getId()+" and expirydate='"+db.getExpirydate()+"'";
        		preparedStatement=con.prepareStatement(sql3);
    			preparedStatement.executeUpdate();
            }
            flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
		
	}

}
