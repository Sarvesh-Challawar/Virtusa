<%@page import="com.mvc.bean.ProductBean"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<title>Warehouse</title>
<script>
	function validate()
	{
		var dt=document.ManualAdd.expirydate.value;
		var arr=dt.split("-");
		var year=arr[2];
		var month=arr[1];
		var day=arr[0];
		var date=new Date(year,month,day);
		var curr=new Date();
	   	if(date<=curr){
	   		alert("Enter future Date!!");
	   		return false;
	   	}else{
	   		var leap=0;
	   		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) 
	        {
	            leap = 1;
	        }
	   		if(month>=1 && month<=12)
	   		{
	   			if(month==2)
	   			{
	   				if(leap==1 && day<=29)
	   				{
	   					
	   				}	
	   				else if(day>28)
	   				{
	   					alert("Invalid Date!!");
	   					return false;
	   				}	
	   			}
	   			else if(month==4 || month==6 || month==9 || month==11)
	   			{
	   				if(day>30)
	   				{
	   					alert("Invalid Date!!");
	   					return false;
	   				}	
	   			}
	   			else if(day>31)
	   			{
	   				alert("Invalid Date!!");
   					return false;
	   			}	
	   		}
	   		else
	   		{
	   			alert("Invalid Date!!");
	   			return false;
	   		}
	   	}
	}
	
	function showTable(){
        if (document.getElementById("displayTable").style.display === "none"){
            document.getElementById("displayTable").style.display="block";
        } else {
            document.getElementById("displayTable").style.display="none";
        }
    }
</script>
</head>
<body>
    <br><br><br><br><br>
    <div class="container  px-3">
        <div class="row align-self-center">
    		<span style="color:red"><%=(request.getAttribute("notif") == null) ? "": request.getAttribute("notif")%></span>                            
    	</div>
    </div>	
    <%
       String usname=(String)session.getAttribute("currentUser");
       if(usname==null) {
            response.sendRedirect("login.jsp");
       }
       String accessright=(String)session.getAttribute("rights");
       HttpServletResponse httpResponse = (HttpServletResponse)response;
       httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
       httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
       httpResponse.setDateHeader("Expires", 0); // Proxies.
     %>
    <div class="container  px-3">
        <div class="row align-self-center">
            Your Username is :<strong> <%=usname %></strong>
        </div>
    </div>
    <div class="container  px-3">
        <div class="row align-self-center">
            You have "<strong> <%=accessright %></strong>" privileges.
        </div>
    </div>
    <nav class="navbar navbar-dark bg-dark navbar-expand-md col-md-12 fixed-top align-self-center">
        <div class="container">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#Navbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand mr-auto pr-3 add-border" href="home.jsp"><img src="images/logo.png" height="60px" width="60px"></a>
            <div class="collapse navbar-collapse" id="Navbar">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a class="nav-link" href="home.jsp"> Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="distributors.jsp"> Distributor Details</a></li>
                    <li class="nav-item active"><a class="nav-link" href="warehouse.jsp"> Warehouse</a></li>
                    <li class="nav-item"><a class="nav-link" href="shelf.jsp"> On shelf</a></li>
                    <li class="nav-item"><a class="nav-link" href="customers.jsp"> Customers</a></li>
                    <li class="nav-item"><a class="nav-link add-border" href="notifications.jsp"> Notifications</a></li>
                    <li class="nav-item"><a class="nav-link add-border" href="settings.jsp"> Settings</a></li>
                	<div class="col-12 col-sm align-self-center">
						<a id="signOut" class="btn btn-sm btn-block btn-warning" role="button" data-toggle="modal" data-target="#SignOut"> Sign Out</a>
					</div>
                </ul>
            </div>
        </div>
    </nav>
    
    <div id="SignOut" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg" role="content">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Sign Out</h4>
                    <button class="close" type="button" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <h4>Do you want to sign out of the application?</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" id="Logout" class="btn btn-danger" onclick="window.location.href='logout.jsp'"> Sign Out</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>      
    </div>
    
    <div class="container">
    	<div class="row">
    		<h2>Options:~</h2>
    	</div>
    </div>
    
    <div class="container">
    	<div class="row row-content">    		
    		<div class="col-12">
    			<form id="FileRead" name="FileRead" method="post" action="AddFileDataController">
	    			<div class="form-group row align-items-center">
	    				<label for="path" class="col-12 col-form-label"><strong>1. Enter full path to the text file:</strong></label>
	    				<div class=" col-9 col-sm-8">
	    					<input type="text" name="path" class="form-control" id="path" required>
	    				</div>
	    				<div class="col-3 col-sm-3">
	    					<input type="submit" class="btn btn-sm btn-block btn-warning" name="AddFile" value="Add File">
	    				</div>
	    			</div>
	    		</form>
	    		<div class="row">
                	<span style="color:red"><%=(request.getAttribute("message") == null) ? "": request.getAttribute("message")%></span>                            
                </div>
    		</div>
    		
    		<div class="col-12">
    			<form id="ManualAdd" name="ManualAdd" method="post" action="AddDataManualController" onsubmit="return validate()">
	    			<div class="form-group row align-items-center"><strong>  2. Add Data Manually:</strong></div>
	    			<div class="form-group row align-items-center">
	    				<label for="pid" class="col-12 col-md-3 col-form-label"><strong>Product ID:</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="number" name="pid" id="pid" class="form-control" placeholder="Enter Products ID" required>
	    				</div>
	    				<div class="col-12 offset-md-3 col-md-10">
                        	<small id="pidHelp" class="text-muted">Must be Numbers only.</small>
                        </div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="vendorcode" class="col-12 col-md-3 col-form-label"><strong>Vendor Code:</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="number" name="vendorcode" id="vendorcode" class="form-control" placeholder="Enter Vendors Code" required>
	    				</div>
	    				<div class="col-12 offset-md-3 col-md-10">
                        	<small id="vcHelp" class="text-muted">Must be Numbers only.</small>
                        </div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="category" class="col-12 col-md-3 col-form-label"><strong>Category:</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="text" name="category" id="category" class="form-control" placeholder="Enter Product Category" required>
	    				</div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="description" class="col-12 col-md-3 col-form-label"><strong>Description:</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="text" name="description" id="description" class="form-control" placeholder="Enter Product Description" required>
	    				</div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="count" class="col-12 col-md-3 col-form-label"><strong>Enter Product Quantity:</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="number" name="count" id="count" class="form-control" placeholder="Enter Product Quantity" required>
	    				</div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="cost" class="col-12 col-md-3 col-form-label"><strong>Enter Product Cost</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="number" name="cost" id="cost" class="form-control" placeholder="Enter Product Cost" required>
	    				</div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="currency" class="col-12 col-md-3 col-form-label"><strong>Enter Currency</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="text" name="currency" id="currency" class="form-control" placeholder="Enter Currency" required>
	    				</div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="expirydate" class="col-12 col-md-3 col-form-label"><strong>Enter Expiry Date</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="text" name="expirydate" id="expirydate" class="form-control" placeholder="Enter Expiry Date" required>
	    				</div>
	    				<div class="col-12 offset-md-3 col-md-10">
                        	<small id="expiryHelp" class="text-muted">Enter date in DD-MM-YYYY format.</small>
                        </div>
	    			</div>
	    			<div class="form-group row align-items-center">
	    				<label for="threshold" class="col-12 col-md-3 col-form-label"><strong>Enter Threshold Value</strong></label>
	    				<div class="col-12 col-md-9">
	    					<input type="text" name="threshold" id="threshold" class="form-control" placeholder="Enter Threshold Value" required>
	    				</div>
	    			</div>
	    			<input type="submit" class="btn btn-success offset-md-3" name="Add" value="Add">
	    			<input type="reset" class="btn btn-default" value="Reset">
	    		</form>
	    		<div class="row">
                	<span style="color:red"><%=(request.getAttribute("message1") == null) ? "": request.getAttribute("message1")%></span>                            
                </div>
    		</div>
    		
    		<div class="col-12">
    			<form name="form" id="form" method="post" action="WarehouseProductsViewController">
    				<div class="form-group row align-items-center"><strong>  3. View Products and Update:</strong></div>
    				<input type="submit" class="btn btn-sm btn-block btn-warning" name="View" value=" View All Products" onclick="showTable()">
    			</form>
    			<div class="container">
    				<div class="row row-content table-responsive">
    					<table id="displayTable" class="table display table-striped table-bordered table-sm table-hover table-fixed" style="width:50%" cellpadding="1" cellspacing="0">
    						<thead>
                            	<tr align="center" class="thead-dark">
                            		<th>ID</th>
                            		<th>Vendor Code</th>
                            		<th>null</th>
                            		<th>Category</th>
                            		<th>Description</th>
                            		<th>Count</th>
                            		<th>Cost</th>
                            		<th>Currency</th>
                            		<th>Expiry Date</th>
                            		<th>Actions</th>
                            	</tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${al}" var="pb">
	                                <tr align="center">
	                                	<td>${pb.id }</td>
	                                	<td>${pb.vendorcode }</td>
	                                	<td>null</td>
	                                	<td>${pb.category }</td>
	                                	<td>${pb.description }</td>
	                                	<td>${pb.count }</td>
	                                	<td>${pb.cost }</td>
	                                	<td>${pb.expirydate }</td>
	                                	<td>${pb.currency }</td>
	                                	<td>
	                                		<a href="editProduct.jsp?id=${pb.id }&vendorcode=${pb.vendorcode}&category='+${pb.category}+'&description='+${pb.description }+'&count=${pb.count }&cost=${pb.cost }&currency='+${pb.currency }+'&expirydate=${pb.expirydate }" class="btn btn-sm btn-block btn-warning">Edit</a><a href="moveProduct.jsp?id=${pb.id }&count=${pb.count}&expirydate=${pb.expirydate }" class="btn btn-sm btn-block btn-danger">Move to shop</a><a href="damagedProduct.jsp?id=${pb.id }&count=${pb.count}&expirydate=${pb.expirydate }" class="btn btn-sm btn-block btn-danger">Damaged</a>
	                                	</td>
	                                </tr>
	                            </c:forEach> 
                            </tbody>
    					</table>
    				</div>
    				<div class="row">
		                <span style="color:red"><%=(request.getAttribute("message2") == null) ? "": "    "+request.getAttribute("message2")%></span>                            
		            </div>
		            <div class="row">
		                <span style="color:red"><%=(request.getAttribute("movemessage") == null) ? "": "    "+request.getAttribute("movemessage")%></span>                            
		            </div>
    			</div>
    		</div>
    	</div>
    </div>
    
    <div class="container">
    	<div class="row row-conotent table-responsive">
    		<table id="displayTable" class="table display table-striped table-bordered table-sm table-hover table-fixed" style="width:50%" cellpadding="1" cellspacing="0">
    			<thead>
                	<tr align="center" class="thead-dark">
                		<th>ID</th>
                		<th>Threshold</th>
                		<th>null</th>
                		<th>Actions</th>
                	</tr>
                </thead>
                <tbody>
                	<c:forEach items="${alu}" var="pb">
	                	<tr align="center">
	                		<td>${pb.id }</td>
	                		<td>${pb.threshold }</td>
	                		<td>null</td>
	                		<td>
	                        	<a href="editThreshold.jsp?id=${pb.id }&threshold=${pb.threshold}" class="btn btn-sm btn-block btn-warning">Edit</a>
	                        </td>
	                	</tr>
	                </c:forEach> 
                </tbody>
    		</table>
    	</div>
    	<div class="row">
			<span style="color:red"><%=(request.getAttribute("message3") == null) ? "": "    "+request.getAttribute("message3")%></span>                            
		</div>
    </div>
    
    <br><br>
    
    <footer class="footer1">
        <div class="container">
            <div class="row">
                <div class="col-4 col-sm-3">
                    <h5>Head office</h5>
                    Sarvesh pvt Ltd.<br>
                </div>
                <div class="col-7 col-sm-5">
                    <h5>Address</h5>
                    <address>
                        shah-ali-banda<br>
                        Hyderabad<br>
                        Telangana, India.<br>
                        Phone : +91 733 727 3753<br>
                        Mail : <a href="mailto:sarvesh1challawar@gmail.com">sarvesh1challawar@gmail.com</a>
                    </address>
                </div>
                <div class="col-12 col-sm-4 align-self-center">
                    <div class="text-center">
                        <a class="btn btn-social-icon btn-facebook" href="http://www.facebook.com/profile.php" target="_blank" title="FaceBook">Facebook</a>
                        <a class="btn btn-social-icon btn-twitter" href="http://twitter.com/" target="_blank" title="Twitter">Twitter</a>
                        <a class="btn btn-social-icon" href="mailto:sarvesh1challawar@gmail.com">Mail</a>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-auto">
                    <p>c Copyrights Sarvesh</p>
                </div>
            </div>
        </div>
    </footer>
    
    <script src="js/jquery.js"></script>
    <script src="js/jquery.dataTables.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/shelf.js"></script>
</body>
</html>        