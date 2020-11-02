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
<title>Home Page</title>
<script>
    function validate()
    {
        var pwd=document.addUserForm.pwd.value;
        var cpwd=document.addUserForm.cpwd.value;
        
        if(pwd==null || pwd=="")
        {
            alert("The password field cannot be empty!");
            return false;
        }
        else if(pwd.length<8 || pwd.length>20)
        {
        	alert("The password length should be between 8 to 20.");
        	return false;
        }
        else if(cpwd==null || cpwd=="")
        {
            alert("The confirm password field cannot be empty!");
            return false;
        }
        else if(pwd!=cpwd)
        {
            alert("Password and Confirm Password do not match!");
            return false;
        }
        else
        {
            var usname='<%=session.getAttribute("rights") %>';
            if(usname!="admin"){
                alert("Only Admin can modify a database");
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
    
    function showForm(){
        var usname='<%=session.getAttribute("rights") %>';
        if(usname!="admin")
        {
        	alert("Only admin can add a new user.");
        }	
        if (document.getElementById("addUserForm").style.display === "none"  && usname==="admin"){
            document.getElementById("addUserForm").style.display="block";
        } else {
            document.getElementById("addUserForm").style.display="none";
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
                    <li class="nav-item active"><a class="nav-link" href="home.jsp"> Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="distributors.jsp"> Distributor Details</a></li>
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
            <div class="col-12 col-md-7">
                <form name="form" id="form" method="post" action="HomeViewController">
                    <input type="submit" class="btn btn-sm btn-block btn-warning" name="View" value="View Users" onclick="showTable()">
                </form>
                <div class="container">
                    <div class="row row-content table-responsive">
                        <table id="displayTable" class="table display table-striped table-bordered table-sm table-hover table-fixed" style="width:50%" cellpadding="1" cellspacing="0">
                            <%--<tr align="center" class="thead-dark">
                                <th>FirstName</th>
                                <th>LastName</th>
                                <th>UserName</th>
                                <th>E-Mail</th>
                                <th>Privilege</th>
                            </tr> --%>
                            <thead>
                            	<tr align="center" class="thead-dark">
	                                <th>FirstName</th>
	                                <th>LastName</th>
	                                <th>UserName</th>
	                                <th>E-Mail</th>
	                                <th>Privilege</th>
                            	</tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${al}" var="ub">
	                                <tr align="center">
	                                    <td>${ub.firstname}</td>
	                                    <td>${ub.lastname}</td>
	                                    <td>${ub.username}</td>
	                                    <td>${ub.email}</td>
	                                    <td>${ub.accessright}</td>
	                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-5">
                <input type="submit" class="btn btn-sm btn-block btn-warning mt-0" name="Add" value="Add Users" onclick="showForm()"><br>
                <form name="addUserForm" id="addUserForm" style="display:none" method="post" action="HomeAddController" onsubmit="return validate()">
                    <div class="form-group row align-items-center">
                    	<label for="name" class="col-12 col-md-3 col-form-label"><strong> Name:</strong></label>
                        <div class="col col-md-5">
                            <input type="text" name="firstName" class="form-control" id="firstName" placeholder="First Name" required/>
                        </div>
                        <div class="col col-md-4">
                          	<input type="text" name="lastName" class="form-control" id="lastName" placeholder="Last Name" required/>
                        </div>
                    </div>
                    <div class="form-group row align-items-center">
                    	<label for="uname" class="col-12 col-md-3 col-form-label"><strong> Username:</strong></label>
                        <div class="col-12 col-md-9">
                           	<input type="text" name="uname" class="form-control inline" id="uname" placeholder="Enter Username" required/>
                        </div>
                    </div>
                    <div class="form-group row align-items-center">
                         <label for="mail" class="col-12 col-md-3 col-form-label"><strong> Email:</strong></label>
                         <div class="col-12 col-md-9">
                         	<input type="email" name="mail" class="form-control" id="mail" placeholder="Enter your Email" required/>
                         </div>
                    </div>
                    <div class="form-group row align-items-center">
                    	<label for="pwd" class="col-12 col-md-3 col-form-label"><strong> Password:</strong></label>
                        <div class="col-10 col-md-9">
                        	<input type="password" name="pwd" class="form-control" id="pwd" placeholder="Enter Password"/>
                        </div>
                        <div class="col-12 offset-md-3 col-md-10">
                        	<small id="passwordHelp" class="text-muted">Must be 8-20 characters long.</small>
                        </div>
                    </div>
                    <div class="form-group row align-items-center">
                    	<label for="cpwd" class="col-12 col-md-3 col-form-label"><strong> Confirm Password:</strong></label>
                        <div class="col-10 col-md-9">
                        	<input type="password" name="cpwd" class="form-control" id="cpwd" placeholder="Confirm Password"/>
                        </div>
                    </div>
                    <div class="form-group row align-items-center">
                    	<label for="privilege" class="col-12 col-form-label"><strong> Select privilege:</strong></label>
                    	<div class="col-12 col-md-6 offset-2 radio">
                    		<label><input type="radio" name="privilege" value="admin">Admin</label>
                    	</div>
                    	<div class="col-12 col-md-6 offset-2 radio">
                    		<label><input type="radio" name="privilege" value="checker" checked>Checker</label>
                    	</div>
                    </div>
                    <input type="submit" class="btn btn-success offset-md-3" name="Add" value="Add">
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