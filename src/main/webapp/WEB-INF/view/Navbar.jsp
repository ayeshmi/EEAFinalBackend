<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
<style>
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
<nav class="navbar navbar-expand-lg fixed-top navbar-scroll shadow-0"
	style="background-color: #00bfff;height:80px;">
	<a class="navbar-brand" href="#" style="font-size: 24px;">DOMSEL</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent" style="font-size: 18px;">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#"><b>Home</b>
					<span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
			<li class="nav-item"><a class="nav-link" href="#"><b>About
						Us</b></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
			<li class="nav-item"><a class="nav-link" href="#"><b>Contact
						Us</b></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <b>Profile</b>
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">

					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="${contextPath}/api/auth/profile">View Profile Details</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="${contextPath}/api/auth/updateProfile">Update Profile Details</a>
				</div></li>
				<li class="nav-item"><a class="nav-link" href="#"></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
			<li class="nav-item"><a class="nav-link" href="#"></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
						<li class="nav-item"><a class="nav-link" href="#"></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
						<li class="nav-item"><a class="nav-link" href="#"></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
						<li class="nav-item"><a class="nav-link" href="#"></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><b>
						</b></a></li>
				 <sec:authorize access="hasAuthority('ROLE_USER')">
        <li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <b>Item</b>
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">

					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="${contextPath}/api/auth/addItemPage">Add New Item</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="${contextPath}/api/auth/viewAllItem">View All Items</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="${contextPath}/api/auth/updateProfile">View Items with categories</a>
				</div></li>
    </sec:authorize>
			
		</ul>
		
	</div>
</nav>