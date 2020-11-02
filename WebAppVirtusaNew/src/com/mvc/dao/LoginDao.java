package com.mvc.dao;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;
 
public class LoginDao {
     public String Authenticate(LoginBean loginBean)
     {
         String userName = loginBean.getUserName(); //Assign user entered values to temporary variables.
         String password = loginBean.getPassword();
 
         Connection con = null;
         Statement statement = null;
         ResultSet resultSet = null;
 
         String userNameDB = "";
         String passwordDB = "";
         String accessright="";
 
         try
         {
             con = DBConnection.createConnection(); //Fetch database connection object
             statement = con.createStatement(); //Statement is used to write queries. Read more about it.
             resultSet = statement.executeQuery("select u.username,u.password,ur.accessright from users u,userrights ur where u.username=ur.username"); //the table name is users and userName,password are columns. Fetching all the records and storing in a resultSet.
 
             while(resultSet.next()) // Until next row is present otherwise it return false
             {
              userNameDB = resultSet.getString("username"); //fetch the values present in database
              passwordDB = resultSet.getString("password");
              accessright = resultSet.getString("accessright");
 
               if(userName.equals(userNameDB) && password.equals(passwordDB))
               {
                  return accessright; ////If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
               }
             }
         }
             catch(SQLException e)
             {
                e.printStackTrace();
             }
             return "Invalid user credentials"; // Return appropriate message in case of failure
         }
     }