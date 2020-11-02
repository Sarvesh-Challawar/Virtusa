<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<title>Invoice</title>
<script>
	function getDue(){
		var total=document.MailInvoiceForm.totalcost.value;
		var paid=document.MailInvoiceForm.paid.value;
		var due=total-paid;
		document.MailInvoiceForm.due.value=due;
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
    
    <%
    	int orderid=Integer.parseInt(request.getParameter("orderid"));
    	String name=request.getParameter("name");
    	String email=request.getParameter("email");
    	String phoneno=request.getParameter("phoneno");
    	String products=request.getParameter("products");
    	int quantity=Integer.parseInt(request.getParameter("quantity"));
    	int totalcost=Integer.parseInt(request.getParameter("totalcost"));
    	int paid=Integer.parseInt(request.getParameter("paid"));
    	int due=Integer.parseInt(request.getParameter("due"));
    %>
    
    <div class="container">
		<div class="d-flex justify-content-center">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-10">
							<h3>Below are the details of the order:~</h3>
						</div>
					</div>
				</div>
				<div class="card-body">
					<form name="MailInvoiceForm" id="MailInvoiceForm" method="post" action="MailInvoiceController" onchange="getDue();">
						<div class="form-group row align-items-center">
							<label for="orderid" class="col-12 col-md-3 col-form-label"><strong>Order ID:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="orderid" id="orderid" class="form-control" value=<%=orderid %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="name" class="col-12 col-md-3 col-form-label"><strong>Name:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="name" id="name" class="form-control" value=<%=name %> required>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="email" class="col-12 col-md-3 col-form-label"><strong>E-Mail ID:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="email" id="email" class="form-control" value=<%=email %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="phoneno" class="col-12 col-md-3 col-form-label"><strong>Phone Number:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="phoneno" id="phoneno" class="form-control" value=<%=phoneno %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="products" class="col-12 col-md-3 col-form-label"><strong>Products:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="text" name="products" id="products" class="form-control" value=<%=products %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="quantity" class="col-12 col-md-3 col-form-label"><strong>Quantity:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="quantity" id="quantity" class="form-control" value=<%=quantity %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="totalcost" class="col-12 col-md-3 col-form-label"><strong>Total Cost:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="totalcost" id="totalcost" class="form-control" value=<%=totalcost %> readonly>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="paid" class="col-12 col-md-3 col-form-label"><strong>Paid Amount:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="paid" id="paid" class="form-control" value=<%=paid %> required>
		    				</div>
                        </div>
                        <div class="form-group row align-items-center">
							<label for="due" class="col-12 col-md-3 col-form-label"><strong>Due Amount:</strong></label>
		    				<div class="col-12 col-md-9">
		    					<input type="number" name="due" id="due" class="form-control" value=<%=due %> required>
		    				</div>
                        </div>
                        <input type="submit" class="btn btn-success offset-3" name="GenerateInvoice" value="Generate Invoice">
                        <button type="button" class="btn btn-default" onclick="location.href='customers.jsp';">Close</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
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
    <script src="js/bootstrap.js"></script>
</body>
</html>