package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.StockBean;
import com.mvc.util.DBConnection;

public class GetStockDAOImpl implements GetStockDAO {

	Connection con=null;
	Statement statement=null;
	ResultSet resultSet=null;
	ResultSet resultSetNew=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public List<StockBean> get() {
		
		List<StockBean> list=null;
		StockBean stb=null;
		try {
			list=new ArrayList<StockBean>();
			String sql1="SELECT id FROM warehouse";
			con=DBConnection.createConnection();
			statement=con.createStatement();
			resultSet=statement.executeQuery(sql1);
			while(resultSet.next()) {
				stb=new StockBean();
				int id=resultSet.getInt("id");
				stb.setId(id);
				int sumw=0;
				int sums=0;
				String sql2="SELECT count FROM products where id="+id;
				statement=con.createStatement();
				resultSetNew=statement.executeQuery(sql2);
				while(resultSetNew.next())
				{
					sumw+=resultSetNew.getInt("count");
				}
				stb.setCountwarehouse(sumw);
				String sql3="SELECT count FROM shopproducts where id="+id;
				statement=con.createStatement();
				resultSetNew=statement.executeQuery(sql3);
				while(resultSetNew.next())
				{
					sums+=resultSetNew.getInt("count");
				}
				stb.setCountshop(sums);
				int total=sumw+sums;
				stb.setTotal(total);;
				list.add(stb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
