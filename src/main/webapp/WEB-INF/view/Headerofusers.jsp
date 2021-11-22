<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<style>

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
<div>
<nav class="navbar navbar-expand-lg fixed-top navbar-scroll shadow-0" style="background-color: #00bfff;">
  <div class="container">
    <a class="navbar-brand" href="#">DOMSEL</a>
    <button class="navbar-toggler ps-0" type="button" data-mdb-toggle="collapse" data-mdb-target="#navbarExample01"
      aria-controls="navbarExample01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="d-flex justify-content-start align-items-center">
        <i class="fas fa-bars"></i>
      </span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarExample01">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item active">
          <a class="nav-link px-3" href="#!"><b></b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!"><b></b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!"><b></b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!"><b></b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!"><b></b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!"><b></b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="${contextPath}/api/auth/home"><b>About Us</b></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link px-3" href="#!"><b>Contact Us</b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!"><b>Home</b></a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="${contextPath}/api/auth/profile"><b>Profile</b></a>
          <ul>
                <li><a href="#">Sub Item</a></li>
                <li><a href="#">Sub Item</a></li>
                <li><a href="#">SUB SUB LIST »</a>
                    <ul>
                        <li><a href="#">Sub Sub Item 1</a>
                            <li><a href="#">Sub Sub Item 2</a>
                    </ul>
                    </li>
            </ul>
        </li>
      </ul>

      <ul class="navbar-nav flex-row">
        <li class="nav-item">
          <a class="nav-link pe-3" href="#!">
            <i class="fab fa-youtube"></i>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!">
            <i class="fab fa-facebook-f"></i>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link ps-3" href="#!">
            <i class="fab fa-instagram"></i>
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</div>
	
	
