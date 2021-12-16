<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>

<style>
	.form12{
background: #eee;
width:1100px;
border-radius: 55px;
  margin: 80px auto;
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
.message{
}

.navbar-scrolled {
  background-color: #ffffff;
}
</style>
<body>
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
<%@include file="ContactUsError.jsp"%>
		<br><br>
	
	<section class="vh-100">
		<div class="form12">
  <div class="container py-5 h-100">
    <div class="row d-flex align-items-center justify-content-center h-100">
      <div class="col-md-8 col-lg-7 col-xl-6">
        <img src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="img-fluid" alt="Phone image">
      </div>
      <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
        <form class="form" id="formElem" action="/api/auth/contactusW" method="POST">
        <h2>Contact Us</h2>
          <!-- Email input -->
          <div class="form-outline mb-4">
            <input type="text" id="form1Example13" class="form-control form-control-lg" name="name" placeholder="Enter your name" style="width:400px" required/>
            <label class="form-label" for="form1Example13">Name</label>
          </div>
          
          <!-- Email input -->
          <div class="form-outline mb-4">
            <input type="text" id="form1Example13" class="form-control form-control-lg" name="email" placeholder="Enter your email" style="width:400px" required/>
            <label class="form-label" for="form1Example13">Email</label>
          </div>
    
          <!-- Password input -->
          <div class="form-outline mb-4">
            <textarea rows = "5" cols = "10" name = "message" class="form-control form-control-lg" placeholder="Enter your message" style="width:400px" required></textarea>
            <label class="form-label" for="form1Example23">Message</label>
          </div>

       

          <!-- Submit button -->
          <button type="submit" class="btn btn-primary btn-lg btn-block" style="width:400px">Submit</button>

         

         

        </form>
      </div>
    </div>
  </div>
  </div>
</section>

</body>
</html>