<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Furniture Product List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<style>
	h1{
		text-align: center;
		font-size: 18px;
	}
	.form12{
background: #eee;
width:1100px;
border-radius: 55px;
  margin: 80px auto;
  height:500px;
}
.navbar-brand {
  letter-spacing: 3px;
  color: #ffffff;
}

.navbar-brand:hover {
	color:#ffffff;
}

.navbar-scroll .nav-link,
.navbar-scroll .fa-bars {
  color: #ffffff;
}

.navbar-scrolled .nav-link,
.navbar-scrolled .fa-bars {
  color: #ffffff;
}

.navbar-scrolled {
  background-color: #ffffff;
}
</style>
<body>
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
<security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.username" var="username"/> 
</security:authorize>
<h1 style="text-align: center;
		font-size: 22px;">Hi , ${username}</h1>
<h1 id="lblGreetings" style="text-align: center;
		font-size: 22px;"></h1>
<h1 style="text-align: center;
		font-size: 22px;">Welcome to  Pharmacist Page</h1>
<section class="vh-100">
	<div class="form12">
<div class="container py-5 h-100">
<div class="row d-flex align-items-center justify-content-center h-100">
  <div class="col-md-8 col-lg-7 col-xl-6">
	<img src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="img-fluid" alt="Phone image">
  </div>
  <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
	<form class="form" id="formElem" action="/api/auth/signinW" method="POST">
	<h2> Pharmacist Responsibilities </h2>
	<ul>
		<li>Handle user accounts</li>
		<li>Handle order requests</li>
		<li>Handle items</li>
		<li>Handle payments</li>
		<li>Handle pharmacist</li>
		<li>Handle deliveries</li>
		
	  </ul> 

	  
	 

	</form>
  </div>
</div>
</div>
</div>
</section>
	

</body>
<script>
    var myDate = new Date();
    var hrs = myDate.getHours();

    var greet;

    if (hrs < 12)
        greet = 'Good Morning';
    else if (hrs >= 12 && hrs <= 17)
        greet = 'Good Afternoon';
    else if (hrs >= 17 && hrs <= 24)
        greet = 'Good Evening';

    document.getElementById('lblGreetings').innerHTML =
         greet;
</script>
</html>