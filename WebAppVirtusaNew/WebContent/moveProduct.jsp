<%@ page import="javax.servlet.RequestDispatcher" %>
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
<title>Move To Shop</title>
<script>
	function validate()
	{
		var countnew=(int)document.moveproducts.count.value;
		var countmax=Integer.parseInt(request.getParameter("count"));
		if(countnew>countmax){
			alert("You only have "+countmax+" products in stock!!");
			return false;
		}
	}
</script>
</head>
<body>
	<%
       String usname=(String)session.getAttribute("currentUser");
       if(usname==null) {
            response.sendRedirect("login.jsp");
       }
       String accessright=(String)session.getAttribute("rights");
     %>

	<%
		if(request.getAttribute("al")==null)
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("/GetLocationController");
			dispatcher.forward(request, response);
		}
	%>
	
	<%
		int id=Integer.parseInt(request.getParameter("id"));
		int countmax=Integer.parseInt(request.getParameter("count"));
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
							<h3>Enter Quantity and Select the Shop location to move to:~</h3>
						</div>
					</div>
				</div>
				<div class="card-body">
					<form name="moveproducts" id="moveproducts" method="post" action="MoveToShopController" onsubmit="return validate()">
						<div class="form-group row align-items-center">
                        	<label for="location" class="col-12 col-md-3 col-form-label"><strong>Select Location:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<select name="location" class="selectpicker" data-style="btn-info" searchable="Search...">
									<c:forEach items="${al}" var="lb">
										<option value="${lb.location }">${lb.location }</option>
									</c:forEach>
								</select>
		    				</div>
                        </div>
						<div class="form-group row align-items-center">
                        	<label for="pid" class="col-12 col-md-3 col-form-label"><strong>Product ID:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="pid" id="pid" class="form-control" value=<%=id %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
		    				<div class="col-12 col-md-9">
		    					<input type="hidden" name="count" id="count" class="form-control" value=<%=countmax %> required>
		    				</div>
		    			</div>
                        <div class="form-group row align-items-center">
                        	<label for="movecount" class="col-12 col-md-3 col-form-label"><strong>Enter quantity to move:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" min="1" max="<%=countmax %>" name="movecount" id="movecount" class="form-control" value=<%=countmax %> required>
		    				</div>
		    			</div>
		    			<div class="form-group row align-items-center">
		    				<div class="col-12 col-md-9">
		    					<input type="hidden" name="expirydate" id="expirydate" class="form-control" value=<%=formatedDate %> readonly>
		    				</div>
		    			</div>
		    			<input type="submit" class="btn btn-success offset-3" name="Move" value="Move">
                        <button type="button" class="btn btn-default" onclick="location.href='warehouse.jsp';">Close</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>