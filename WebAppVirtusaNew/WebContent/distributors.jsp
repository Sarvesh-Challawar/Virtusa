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
<title>Distributors</title>
<script>
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
     
     <div class="container  mt-auto">
        <div class="row align-self-center">
            User is :<strong> <%=usname %></strong>
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
                    <li class="nav-item active"><a class="nav-link" href="distributors.jsp"> Distributor Details</a></li>
                    <li class="nav-item"><a class="nav-link" href="warehouse.jsp"> Warehouse</a></li>
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
            <div class="col-12 align-self-center">
                <h2>Options:~</h2>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="row row-content">
            <div class="col-12 col-sm-12 align-self-center">
                <form name="form" id="form" method="post" action="DistributorViewController">
                    <input type="submit" class="btn btn-sm btn-block btn-warning" name="View" value="View Distributors" onclick="showTable()">
                </form>
                <div class="container">
                    <div class="row row-content table-responsive">
                        <table id="displayTable" class="table display table-striped table-bordered table-sm table-hover table-fixed" style="width:50%" cellpadding="1" cellspacing="0" border="3px">
                            <thead>
                            	<tr align="center" class="thead-dark">
	                            	<th>Vendor Code</th>
	                                <th>FirstName</th>
	                                <th>LastName</th>
	                                <th>Company Name</th>
	                                <th>Address</th>
	                                <th>E-Mail</th>
	                                <th>Phone no.</th>
                            	</tr>
                            </thead>
                            <tbody>
	                            <c:forEach items="${al}" var="db">
	                                <tr align="center">
	                                	<td>${db.vendorcode }</td>
	                                    <td>${db.firstname}</td>
	                                    <td>${db.lastname}</td>
	                                    <td>${db.companyname}</td>
	                                    <td>${db.address }</td>
	                                    <td>${db.email}</td>
	                                    <td>${db.phoneno }</td>
	                                </tr>
	                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-12 col-sm-12">
                <input type="submit" class="btn btn-sm btn-block btn-warning mt-0" name="Add" value="Add Distributor"><br>
                	<form name="addDistributorForm" id="addDistributorForm" method="post" action="DistributorAddController">
                        <div class="form-group row align-items-center">
                            <label for="name" class="col-12 col-sm-2 col-form-label"><strong> Name:</strong></label>
                            <div class="col col-sm-5">
                                <input type="text" name="firstName" class="form-control" id="firstName" placeholder="First Name" required/>
                            </div>
                            <div class="col col-sm-5">
                                <input type="text" name="lastName" class="form-control" id="lastName" placeholder="Last Name" required/>
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="cname" class="col-12 col-sm-2 col-form-label"><strong>Distributors's Name:</strong></label>
                            <div class="col col-sm-10">
                                <input type="text" name="cname" class="form-control" id="cname" placeholder="Enter the Distributors's name" required="/">
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="address" class="col-12 col-sm-2 col-form-label"><strong>Distributors's Address:</strong></label>
                            <div class="col col-sm-10">
                                <input type="text" name="address" class="form-control" id="address" placeholder="Enter the Distributors's address" required="/">
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="cmail" class="col-12 col-sm-2 col-form-label"><strong>Distributors's Mail:</strong></label>
                            <div class="col col-sm-10">
                                <input type="email" name="cmail" class="form-control" id="cmail" placeholder="Enter the Distributors's mail" required="/">
                            </div>
                        </div>
                        <div class="form-group row align-items-center">
                            <label for="cphone" class="col-12 col-sm-2 col-form-label"><strong>Distributors's Phone:</strong></label>
                            <div class="col col-sm-10">
                                <input type="text" name="cphone" class="form-control" id="cphone" placeholder="Enter the Distributors's phone" required="/">
                            </div>
                        </div>
                        <input type="submit" class="btn btn-success offset-2" name="Add" value="Add">
                        <input type="reset" class="btn btn-default" value="Reset">
                    </form>
                    <div class="row">
                       	<span style="color:red"><%=(request.getAttribute("message") == null) ? "": request.getAttribute("message")%></span>                            
                    </div>
			</div>
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