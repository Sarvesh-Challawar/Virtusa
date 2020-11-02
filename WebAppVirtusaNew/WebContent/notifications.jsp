<%@ page import="javax.servlet.RequestDispatcher" %>
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
<title>Notifications</title>
</head>
<body>
    <br><br><br><br><br>
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
                    <li class="nav-item"><a class="nav-link" href="warehouse.jsp"> Warehouse</a></li>
                    <li class="nav-item"><a class="nav-link" href="shelf.jsp"> On shelf</a></li>
                    <li class="nav-item"><a class="nav-link" href="customers.jsp"> Customers</a></li>
                    <li class="nav-item active"><a class="nav-link add-border" href="notifications.jsp"> Notifications</a></li>
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
	
	<%
		if(request.getAttribute("al")==null)
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("/ThresholdNotificationController");
			dispatcher.forward(request, response);
		}
	%>
	<div class="container">
		<div class="row">
			<h2>Low stocks for products with id's:</h2>
		</div>
		<div class="row row-content table-responsive">
			<table id="" class="table display table-striped table-bordered table-sm table-hover table-fixed" style="width:50%" cellpadding="1" cellspacing="0">
				<thead>
					<tr align="center" class="thead-dark">
						<th>ID</th>
						<th>Threshold</th>
						<th>null</th>
						<th>Category</th>
						<th>Description</th>
						<th>Available count</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${al}" var="pb">
	                	<tr align="center">
							<td>${pb.id }</td>
							<td>${pb.threshold }</td>
							<td>null</td>
							<td>${pb.category }</td>
							<td>${pb.description }</td>
							<td>${pb.count }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<%
		if(request.getAttribute("al1")==null)
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("/ExpiryNotificationController");
			dispatcher.forward(request, response);
		}
	%>
	<div class="container">
		<div class="row">
			<h2>Expiry date closer for products with id's:</h2>
		</div>
		<div class="row row-content table-responsive">
			<table id="" class="table display table-striped table-bordered table-sm table-hover table-fixed" style="width:50%" cellpadding="1" cellspacing="0">
				<thead>
					<tr align="center" class="thead-dark">
						<th>ID</th>
						<th>Category</th>
						<th>null</th>
						<th>Description</th>
						<th>Available count</th>
						<th>Expiry date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${al1}" var="pb">
	                	<tr align="center">
							<td>${pb.id }</td>
							<td>${pb.category }</td>
							<td>null</td>
							<td>${pb.description }</td>
							<td>${pb.count }</td>
							<td>${pb.expirydate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
    <script src="js/common.js"></script>
</body>
</html>                