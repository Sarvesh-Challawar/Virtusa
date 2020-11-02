<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.Calendar" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<title>Edit product details</title>
</head>
<body>
	<%
       String usname=null;
       if(session.getAttribute("currentUser")==null) {
            response.sendRedirect("login.jsp");
       }else{
            usname=(String)session.getAttribute("currentUser");
       }
     %>
     
     <%
     	int id=Integer.parseInt(request.getParameter("id"));
     	int vendorcode=Integer.parseInt(request.getParameter("vendorcode"));
     	String category=request.getParameter("category");
     	String description=request.getParameter("description");
     	int count=Integer.parseInt(request.getParameter("count"));
     	double cost=Double.parseDouble(request.getParameter("cost"));
     	String currency=request.getParameter("currency");
     	
     	//java.text.SimpleDateFormat oldFormater = new SimpleDateFormat("dd-mm-yyyy");
     	//java.util.Date resultInDate = oldFormater.parse(request.getParameter("expirydate"));
     	//System.out.println(resultInDate);
     	String d=request.getParameter("expirydate");
     	DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = (Date)formatter.parse(d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +         cal.get(Calendar.YEAR);
     %>
     
     <div class="container">
		<div class="d-flex justify-content-center">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-10">
							<h3>Modify form values to change:~</h3>
						</div>
					</div>
				</div>
				<div class="card-body">
					<form name="EditProductForm" id="EditProductForm" method="post" action="EditProductDataController">
						<div class="form-group row align-items-center">
                        	<label for="pid" class="col-12 col-md-3 col-form-label"><strong>Product ID:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="pid" id="pid" class="form-control" value=<%=id %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
		    				<label for="vendorcode" class="col-12 col-md-3 col-form-label"><strong>Vendor Code:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="vendorcode" id="vendorcode" class="form-control" value=<%=vendorcode %> readonly>
		    				</div>
	    				</div>
	    				<div class="form-group row align-items-center">
		    				<label for="category" class="col-12 col-md-3 col-form-label"><strong>Category:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="category" id="category" class="form-control" value=<%=category %> required>
		    				</div>
		    			</div>
		    			<div class="form-group row align-items-center">
		    				<label for="description" class="col-12 col-md-3 col-form-label"><strong>Description:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="description" id="description" class="form-control" value=<%=description %> required>
		    				</div>
		    			</div>
		    			<div class="form-group row align-items-center">
		    				<label for="count" class="col-12 col-md-3 col-form-label"><strong>Enter Product Quantity:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="count" id="count" class="form-control" value=<%=count %> required>
		    				</div>
		    			</div>
		    			<div class="form-group row align-items-center">
		    				<label for="cost" class="col-12 col-md-3 col-form-label"><strong>Enter Product Cost</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="cost" id="cost" class="form-control" value=<%=cost %> required>
		    				</div>
		    			</div>
		    			<div class="form-group row align-items-center">
		    				<label for="currency" class="col-12 col-md-3 col-form-label"><strong>Enter Currency</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="currency" id="currency" class="form-control" value=<%=currency %> required>
		    				</div>
		    			</div>
		    			<div class="form-group row align-items-center">
		    				<label for="expirydate" class="col-12 col-md-3 col-form-label"><strong>Expiry date</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="expirydate" id="expirydate" class="form-control" value=<%=formatedDate %> readonly>
		    				</div>
		    			</div>
		    			<input type="submit" class="btn btn-success offset-3" name="Edit" value="Edit">
                        <button type="button" class="btn btn-default" onclick="location.href='warehouse.jsp';">Close</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>     