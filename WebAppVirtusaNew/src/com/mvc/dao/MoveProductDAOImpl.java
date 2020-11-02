package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.bean.MoveBean;
import com.mvc.util.DBConnection;

public class MoveProductDAOImpl implements MoveProductDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public String move(MoveBean mb) {
		
		String flag="";
		try {
			con=DBConnection.createConnection();
			int countDB=0;
			String sql="SELECT count from products where id="+mb.getId()+" and expirydate='"+mb.getExpirydate()+"'";
			statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
            	countDB=resultSet.getInt("count");
            }
			String check="SELECT * from shopproducts where location='"+mb.getLocation()+"' and id="+mb.getId()+" and expirydate='"+mb.getExpirydate()+"'";
			statement = con.createStatement();
            resultSet = statement.executeQuery(check);
            if(resultSet.next()) {
            	int oldcount=resultSet.getInt("count");
            	int newcount=mb.getMovecount()+oldcount;
            	String sql1="UPDATE shopproducts SET count="+newcount+" where location='"+mb.getLocation()+"' and id="+mb.getId()+" and expirydate='"+mb.getExpirydate()+"'";
            	preparedStatement=con.prepareStatement(sql1);
    			preparedStatement.executeUpdate();
            	int leftcount=countDB-mb.getMovecount();
            	System.out.println(leftcount);
            	if(leftcount==0) {
            		flag+=mb.getId()+" ";
            		String sql2="DELETE from products where id="+mb.getId()+" and expirydate='"+mb.getExpirydate()+"'";
            		preparedStatement=con.prepareStatement(sql2);
        			preparedStatement.executeUpdate();
            	}else {
            		//update products
            		String sql2="UPDATE products SET count="+leftcount+" where id="+mb.getId()+" and expirydate='"+mb.getExpirydate()+"'";
            		preparedStatement=con.prepareStatement(sql2);
        			preparedStatement.executeUpdate();
            	}
            }else {
            	String location=mb.getLocation();
            	int id=mb.getId();
            	String category="";
            	String description="";
            	int movecount=mb.getMovecount();
            	int leftcount=countDB-movecount;
            	double cost=0.0;
            	String currency="";
            	String sql1="SELECT * from products where id="+mb.getId()+" and expirydate='"+mb.getExpirydate()+"'";
            	statement = con.createStatement();
                resultSet = statement.executeQuery(sql1);
                if(resultSet.next()) {
                	category=resultSet.getString("category");
                	description=resultSet.getString("description");
                	cost=resultSet.getDouble("cost");
                	currency=resultSet.getString("currency");
                }
                String sql2="INSERT INTO shopproducts(location,id,category,description,count,cost,currency,expirydate) values('"+location+"', "+id+", '"+category+"', '"+description+"', "+movecount+", "+cost+", '"+currency+"', '"+mb.getExpirydate()+"')";
                preparedStatement=con.prepareStatement(sql2);
    			preparedStatement.executeUpdate();
    			String sql3="UPDATE products SET count="+leftcount+" where id="+mb.getId()+" and expirydate='"+mb.getExpirydate()+"'";
        		preparedStatement=con.prepareStatement(sql3);
    			preparedStatement.executeUpdate();
            }
            String thcheck="SELECT w.id from Warehouse w,products p where w.id=p.id and p.count<w.threshold";
            statement = con.createStatement();
            resultSet = statement.executeQuery(thcheck);
            if(resultSet.next()) {
            	flag+=resultSet.getInt("id")+" ";
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
		
	}

}
