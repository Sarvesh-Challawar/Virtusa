<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/common.css">
<title>Login Page</title>
<script>
	function validate(){
		var uname=document.loginForm.uname.value;
		var pwd=document.loginForm.pass.value;
		if(uname==null || uname==""){
			alert("Please Enter a username to login!!");
			return false;
		}
		else if(pwd==null || pwd==""){
			alert("Password cannot be empty!!");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<header class="jumbotron">
			<div class="row row-header">
				<div class="col-12 col-sm-4 align-self-center">
					<img src="images/logo.png" class="img-fluid rounded img-thumbnail" alt="logo:missing">
				</div>
				<div class="col col-sm-6">
					<h1>Sarvesh pvt Ltd.</h1>
					<p>Manage stocks at a click.</p>
				</div>
			</div>
		</header>
	</div>
	
	<br><br>
	<div class="container">
		<div class="d-flex justify-content-center">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-10">
							<h3>Login Here:~</h3>
						</div>
					</div>
				</div>
				<div class="card-body">
					<form name="loginForm" id="loginForm" method="post" action="LoginServlet" onsubmit="return validate()")>
						<div class="form-group row align-items-center">
							<label for="uname" class="col-12 col-sm-4 col-form-label"><strong> Username:</strong></label>
							<div class="col-12 col-sm-8">
								<input type="text" name="uname" class="form-control inline" id="uname" placeholder="Enter Username"/>
							</div>
						</div>
						<div class="form-group row align-items-center">
							<label for="pass" class="col-12 col-sm-4 col-form-label"><strong> Password:</strong></label>
							<div class="col-10 col-sm-8">
								<input type="password" name="pass" class="form-control" id="pass" placeholder="Enter Password"/>
							</div>
						</div>
						<div class="form-group row align-items-center">
							<div class="col-12 offset-4 col-sm-12">
								<button type="submit" class="btn btn-success">Sign In</button>
								<input type="reset" class="btn btn-default" value="reset">
							</div>
						</div>
						<div class="row">
							<span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></span>
						</div>
					</form>
				</div>
				<!-- <div class="card-footer">
					<div class="d-flex justify-content-center links">
						<a href="#"> Forgot Password?</a>
					</div>
				</div>-->
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
    <script src="js/bootstrap.js"></script>
    <script src="js/common.js"></script>	
	
</body>
</html>