<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
</style>

<body>
	<nav class="navbar navbar-expand-lg fixed-top navbar-scroll shadow-0"
		style="background-color: #000080;">
		<div class="container">
			<a class="navbar-brand" href="#">DOMSEL</a>
			<button class="navbar-toggler ps-0" type="button"
				data-mdb-toggle="collapse" data-mdb-target="#navbarExample01"
				aria-controls="navbarExample01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="d-flex justify-content-start align-items-center">
					<i class="fas fa-bars"></i>
				</span>
			</button>
			<div class="collapse navbar-collapse" id="navbarExample01">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item active"><a class="nav-link px-3" href="#!"><b>Login</b></a>
					</li>
					<li class="nav-item"><a class="nav-link px-3" href="#!"><b>Register</b></a>
					</li>
					<li class="nav-item"><a class="nav-link px-3" href="#!"><b>About
								Us</b></a></li>
					<li class="nav-item active"><a class="nav-link px-3" href="#!"><b>Contact
								Us</b></a></li>
					<li class="nav-item"><a class="nav-link px-3" href="#!"><b>Home</b></a>
					</li>
				</ul>

				<ul class="navbar-nav flex-row">
					<li class="nav-item"><a class="nav-link pe-3" href="#!"> <i
							class="fab fa-youtube"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link px-3" href="#!"> <i
							class="fab fa-facebook-f"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link ps-3" href="#!"> <i
							class="fab fa-instagram"></i>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<br>

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
						<form class="form" id="formElem" action="/api/auth/register"
							method="POST">
							<h2>Register</h2>
<!-- Email input -->
							<div class="form-outline mb-4">
								<input type="text" id="form1Example13"
									class="form-control form-control-lg" name="username" /> <label
									class="form-label" for="form1Example13">Username</label>
							</div>

							<!-- Email input -->
							<div class="form-outline mb-4">
								<input type="text" id="form1Example13"
									class="form-control form-control-lg" name="email" /> <label
									class="form-label" for="form1Example13">Email</label>
							</div>
							
							<!-- Password input -->
							<div class="form-outline mb-4">
								<input type="password" id="form1Example23"
									class="form-control form-control-lg" name="password" /> <label
									class="form-label" for="form1Example23">Password</label>
							</div>

							<div
								class="d-flex justify-content-around align-items-center mb-4">
								<!-- Checkbox -->
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="form1Example3" checked /> <label
										class="form-check-label" for="form1Example3"> Remember
										me </label>
								</div>
								<a href="#!" class="passworda">Forgot password?</a>
							</div>

							<!-- Submit button -->
							<button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>




						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<hr>



</body>
</html>