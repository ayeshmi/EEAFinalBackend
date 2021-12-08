<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.form12 {
	background: #eee;
	width: 1100px;
	border-radius: 55px;
	margin: 80px auto;
	height:600px;
}

.navbar-brand {
	letter-spacing: 3px;
	color: #ffffff;
}

.navbar-brand:hover {
	color: #ffffff;
}

.navbar-scroll .nav-link, .navbar-scroll .fa-bars {
	color: #ffffff;
}

.navbar-scrolled .nav-link, .navbar-scrolled .fa-bars {
	color: #ffffff;
}

.navbar-scrolled {
	background-color: #ffffff;
}
input{
width:100px;
}
</style>

<body>
	<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>

	<section class="vh-100">
		<div class="form12">
			<div class="container py-5 h-100">
				<div
					class="row d-flex align-items-center justify-content-center h-100">
					<div class="col-md-8 col-lg-7 col-xl-6">
						<img
							src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
							class="img-fluid" alt="Phone image">
					</div>
					<div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
						<form class="form" id="formElem" action="/api/auth/addPharmacient"
							method="POST" enctype="multipart/form-data">
							<h2>Add Pharmacist</h2>
<!-- Email input -->
							<div class="form-outline mb-4">
								<input type="text" id="form1Example13"
									class="form-control form-control-lg" name="firstName" /> <label
									class="form-label" for="form1Example13">First Name</label>
							</div>

							<!-- Email input -->
							<div class="form-outline mb-4">
								<input type="text" id="form1Example13"
									class="form-control form-control-lg" name="lastName" /> <label
									class="form-label" for="form1Example13">Last Name</label>
							</div>
							
							<!-- Password input -->
							<div class="form-outline mb-4">
								<input type="text" id="form1Example23"
									class="form-control form-control-lg" name="contactNumber" /> <label
									class="form-label" for="form1Example23">Contact Number</label>
							</div>
							
							<div class="form-outline mb-4">
								<input type="text" id="form1Example23"
									class="form-control form-control-lg" name="email" /> <label
									class="form-label" for="form1Example23">Email</label>
							</div>
							
							<div class="form-outline mb-4">
								<input type="text" id="form1Example23"
									class="form-control form-control-lg" name="address" /> <label
									class="form-label" for="form1Example23">Address</label>
							</div>
							
							<label for="inputAddress">Pharmacist image</label>
          <input type="hidden" name="role" value="pharmacist" />
              <input type="file" name="image" 
                id="customFile" value="Click the above button to upload the image" style="height: 100px;">

							<!-- Submit button -->
							<button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>




						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<hr>



</body>
</html>