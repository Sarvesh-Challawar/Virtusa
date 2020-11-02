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
<title>Edit your details</title>
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
    }
</script>
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
     	String firstname=request.getParameter("firstname");
     	String lastname=request.getParameter("lastname");
     	String email=request.getParameter("email");
     	String password=request.getParameter("password");
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
					<form name="EditUserForm" id="EditUserForm" method="post" action="EditUserDataController" onsubmit="return validate()">
                    	<div class="form-group row align-items-center">
                                <label for="name" class="col-12 col-sm-2 col-form-label"><strong> Name:</strong></label>
                                <div class="col col-sm-5">
                                    <input type="text" name="firstName" class="form-control" id="firstName" value=<%=firstname %> required/>
                                </div>
                                <div class="col col-sm-5">
                                    <input type="text" name="lastName" class="form-control" id="lastName" value=<%=lastname %> required/>
                                </div>
                            </div>
                        	<input type="hidden" name="uname" id="uname" value=<%=usname %> />
                            <div class="form-group row align-items-center">
                                <label for="mail" class="col-12 col-sm-2 col-form-label"><strong> Email:</strong></label>
                                <div class="col-12 col-sm-9">
                                    <input type="email" name="mail" class="form-control" id="mail" value=<%=email %> required/>
                                </div>
                            </div>
                            <div class="form-group row align-items-center">
                                <label for="pwd" class="col-12 col-sm-2 col-form-label"><strong> Password:</strong></label>
                                <div class="col-10 col-sm-6">
                                    <input type="text" name="pwd" class="form-control" id="pwd" value=<%=password %>>
                                </div>
                                <div class="col-12 offset-2 col-sm-12">
                                    <small id="passwordHelp" class="text-muted">Must be 8-20 characters long.</small>
                                </div>
                            </div>
                            <div class="form-group row align-items-center">
                                <label for="cpwd" class="col-12 col-sm-2 col-form-label"><strong> Confirm Password:</strong></label>
                                <div class="col-10 col-sm-6">
                                    <input type="text" name="cpwd" class="form-control" id="cpwd" value=<%=password %>>
                                </div>
                            </div>
                            <input type="submit" class="btn btn-success offset-2" name="Edit" value="Edit">
                            <button type="button" class="btn btn-default" onclick="location.href='settings.jsp';">Close</button>
                            
                    </form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>