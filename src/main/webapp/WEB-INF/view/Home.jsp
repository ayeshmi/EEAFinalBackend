<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>

<title>Employee Management System</title>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
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
video {
    object-fit: cover;
    width: 100%;
    height: 100%;
    position: fixed;
    z-index: -1;
  }
  
  .hero-container {
    /* background: url('../assets/images/img-home.jpg') center center/cover no-repeat; */
    height: 100vh;
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    box-shadow: inset 0 0 0 1000px rgba(0,0,0,0.2);
    object-fit: contain;
  }
  
  .hero-container > h1 {
    color: #fff;
    font-size: 100px;
    margin-top: -100px;
  }
  
  .hero-container > p {
    margin-top: 8px;
    color: #fff;
    font-size: 37px;
    font-family: 'Trebuchet MS',"Locida Sans Unicode", "Lucida Grande", Arial, sans-sefif;
  }
  
  .hero-btns {
    margin-top: 32px;
  }
  
  .hero-btns .btn {
    margin: 6px;
  }
  /* Add some content at the bottom of the video/page */
.content {
  position: fixed;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  color: #f1f1f1;
  width: 100%;
  padding: 20px;
}
/* Style the video: 100% width and height to cover the entire window */
#myVideo {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
}



/* Style the button used to pause/play the video */
#myBtn {
  width: 200px;
  font-size: 18px;
  padding: 10px;
  border: none;
  background: #00bfff;
  color: #fff;
  cursor: pointer;
}

#myBtn:hover {
  background: #ddd;
  color: black;
}
</style>
<body>
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
	
	<section class="vh-100">
	<!-- The video -->
<video autoplay muted loop id="myVideo">
  <source src="https://cdn.videvo.net/videvo_files/video/free/2016-07/large_watermarked/160714_Pills_2_1080p_preview.mp4" type="video/mp4">
</video>

<!-- Optional: some overlay text to describe the video -->
<div class="content">
  <h1>DOMSEL PHARMACY</h1>
  <p>Buy Safely<br></br>
Purchase medication from legitimate websites online. Search for a site to see if it is safe or not recommended.

Check to see if a site is safe:</p>
  <!-- Use a button to pause/play the video with JavaScript -->
  <a type="button" id="myBtn" onclick="myFunction()" href = "${contextPath}/api/auth/a" >GET STARTED</a>
</div>
  </section>
		
		
	
	
</body>
</html>