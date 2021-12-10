<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
	
</head>
    <style>
        .form12{
background: #eee;
width:750px;
border-radius: 55px;
  margin: 80px auto;
  height:850px;
}
#headerTitle1{
  text-align: center;
  font-family: 'open sans', sans-serif;
  padding: 0rem 0;
  margin: 0;
  font-size: 2.0rem;
}
.row1 label{
  align-self: start;
  padding-left: 7.5rem;
  padding-bottom: 0.5rem;
  color: rgba(17, 11, 11, 0.9);
}
.row1{
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 1rem;
  max-width: 100%;
  
}
.row1 input{
  width: 300px;
  height:40px;
  box-sizing: border-box;
  border: none;
  font-size: 15px;
  padding-left: 1.5rem;
  padding-bottom: 1rem;
  box-shadow: inset 0px -3px 0px 0px rgba(187,187,187,0.2);
  transition: box-shadow 0.2s ease-in;
}

.row1 input:focus{
   box-shadow: inset 0px -3px 0px 0px rgba(34,193,195,0.7);
   outline: none;
}

.row1 input::-webkit-input-placeholder{
  opacity: 1;
  transition: opacity 0.25s ease-out;
}

.row1 input:hover::-webkit-input-placeholder,
.row1 input:focus::-webkit-input-placeholder{
  opacity: 0;
}
.form2{
  max-width: 800px;
  min-width: 295px;
  height: 815px;
  
  top:7%;
  left: 50%;
  background-color: #FFFFFF;
  border-radius: 55px;
  margin: 80px auto;
  position: absolute;
}
label{
  display: flex;
  flex-direction: column;
  align-items: left;
  padding-top: 2rem;
  max-width: 100%;
  align-self: start;
  padding-left: 4.5rem;
  padding-bottom: 0.5rem;
  color: rgba(14, 6, 6, 0.9);
}
.row1 button{
  border-radius: 25px;
  width: 50%;
  height: 40px;
  font-size: 1.3rem;
  color: white;
  font-weight: 700;
  background: rgb(234, 235, 240);
  background: linear-gradient(135deg, rgb(122, 97, 180) 0%,     rgb(77, 143, 209) 100%);
  border: 0px;
  cursor: pointer;
  transition: opacity 0.25s ease-out;
}
.form-img22{
    width: 200px;
    height: 200px;
}
    </style>
<body>

<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
<security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.email" var="email"/> 
</security:authorize>
<security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.username" var="username"/> 
</security:authorize>
<%@include file="ViewAllError.jsp" %>
<div >
        <form class="row1 form12" action="/api/auth/updateUser" method="POST" enctype="multipart/form-data">
            <h1 id="headerTitle1"><b>View User Details.</b></h1>
            <br></br>
            <img class='form-img22'  src=${user.image} alt='register' />
                <label for="inputAddress">Username</label>
                <input type="text" class="form-control" id="inputAddress" placeholder="Username" value=${user.username} name="username" disabled>
              
                <label for="inputAddress">Email</label>
                <input type="text" class="form-control" id="inputAddress" placeholder="Email" name="email" value=${user.email} disabled>

                <label for="inputAddress">BirthDay</label>
                <input type="date" class="form-control" id="inputAddress" placeholder="BirthDay" name="birthday" value=${user.birthday}>

                <label for="inputAddress">Address</label>
                <input type="text" class="form-control" id="inputAddress" placeholder="Address" name="address" value=${user.address}>
                
                <label for="inputAddress">Item image</label>
          
              <input type="file" name="image" 
                id="customFile" value="Click the above button to upload the image" style="height: 100px;">
            
            <input type="hidden" name="email" value=${email} />
             <input type="hidden" name="username" value=${username} />
            <button>Submit</button>
          </form>
        </div>

</body>
</html>