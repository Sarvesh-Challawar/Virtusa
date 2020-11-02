<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<title>Edit Threshold values</title>
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
     	int threshold=Integer.parseInt(request.getParameter("threshold"));
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
					<form name="EditThresholdForm" id="EditThresholdForm" method="post" action="EditThresholdController">
						<div class="form-group row align-items-center">
                        	<label for="pid" class="col-12 col-md-3 col-form-label"><strong>Product ID:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="pid" id="pid" class="form-control" value=<%=id %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
		    				<label for="threshold" class="col-12 col-md-3 col-form-label"><strong>Threshold Value:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="threshold" id="threshold" class="form-control" value=<%=threshold %> required>
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