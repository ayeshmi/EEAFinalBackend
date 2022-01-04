<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.form12 {
	background: rgb(76, 26, 194);
	background: linear-gradient(135deg, rgb(71, 169, 194) 0%,
		rgb(19, 21, 131) 100%);
	width: 750px;
	border-radius: 55px;
	margin: 80px auto;
	height: 450px;
}



.row1 label {
    color: white;
	align-self: start;
	padding-left: 7.5rem;
	padding-bottom: 0.5rem;

}

.row1 {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding-top: 1rem;
	max-width: 100%;
	min-width: 100px;
}

.row1 input {
	width: 500px;
	height: 50px;
	box-sizing: border-box;
	border: none;
	font-size: 17px;
	padding-left: 1.5rem;
	padding-bottom: 2rem;
	box-shadow: inset 0px -3px 0px 0px rgba(187, 187, 187, 0.2);
	transition: box-shadow 0.2s ease-in;
}
.row1 button {
	border-radius: 25px;
	width: 200px;
	height: 50px;
	font-size: 1.3rem;
	color: white;
	font-weight: 700;
	background: rgb(234, 235, 240);
	background: linear-gradient(135deg, rgb(122, 97, 180) 0%,
		rgb(77, 143, 209) 100%);
	border: 0px;
	cursor: pointer;
	transition: opacity 0.25s ease-out;
}

.inputs{
width:200px;
height:200px;
}

</style>
<body>
<security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.email" var="email"/> 
</security:authorize>
<security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.username" var="username"/> 
</security:authorize>
<security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.id" var="id"/> 
</security:authorize>
<jsp:include page="Navbar.jsp">
		<jsp:param name="page2" value="home2" />
	</jsp:include>
	
	<%@include file="ViewAllError.jsp" %>
	<form class="row1 form12" action="/api/auth/addAttendence" method="post"
		enctype="multipart/form-data" id="ayeshmi">
		<h1 id="headerTitle1">
			<b>Attendance</b>
		</h1>
		<input hidden value=${email} name="email">
		<input hidden value=${username} name="username">
		<input hidden value=${id} name="id">
		<label for="appt">Select start time:</label>
  <input type="time" id="appt" name="startTime" class="inputs">
   <br></br>
  <label for="appt">Select leave time:</label>
  <input type="time" id="appt" name="endTime">
  <br></br>
		<button type="file">Submit</button>
		
	</form>

</body>
</html>