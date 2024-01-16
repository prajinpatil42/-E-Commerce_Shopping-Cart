<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="cn.techtutorial.model.*"%>
	<%@page import="java.util.*"%>
	
	    <% 
    User auth=(User) request.getSession().getAttribute("auth");
    if(auth!= null){
    	response.sendRedirect( "index.jsp");
    }
    
    ArrayList<Cart> cart_list= (ArrayList<Cart> ) session.getAttribute("cart-list");

    if(cart_list !=null){
    	request.setAttribute("cart_list",cart_list);
    	
    	
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Shopping cart login</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
<%@include file="includes/navbar1.jsp" %>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">


					<div class="form-group">
						<label>Email Address</label> <input class="form-control
"
							type="email" name="login-email" placeholder="Enter Your Email"
							required>
					</div>

					<div class="form-group">
						<label>Password</label> <input class="form-control
"
							type="password" name="login-password" placeholder="********"
							required>
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>


				</form>
			</div>
		</div>
	</div>



	<%@include file="includes/footer.jsp"%>
</body>
</html>