<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<style>
    .button1{
  display: flex;
  flex-direction: column;
  align-items: center;
  
  max-width: 100%;
  border-radius: 25px;
  width: 80%;
  height: 40px;
  font-size: 1.3rem;
  color: white;
  
  background: rgb(111, 129, 216);
  background: linear-gradient(90deg, rgb(87, 140, 184) 0%,     rgb(57, 162, 180) 100%);
  border: 0px;
  cursor: pointer;
  transition: opacity 0.25s ease-out;
}
.form-img22{
    width: 100px;
    height: 100px;
}
</style>
<body>
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
<br><br>
 <%@include file="Message.jsp" %>
 <%@include file="ErrorMessage.jsp" %>
 <%@include file="ViewAllError.jsp" %>

<%@include file="AdvanceSerachPharmacist.jsp" %>
<br></br>
    <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Image</th>
            <th scope="col">Email</th>
            <th scope="col">Contact Number</th>
            <th scope="col">Address</th>
            <th scope="col">View</th>
            <th scope="col">Delete</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${pharmacients}">
          <tr>
            
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><img class='form-img22'  src=${user.image} alt='image' /></td>
            <td>${user.email}</td>
            <td>${user.contactNumber}</td>
            <td>${user.address}</td>
            <td><a type="button" class="btn btn btn-warning"  href = "${contextPath}/api/auth/viewPharmacientByItem/${user.id}"
                       style="margin-left: 5px;"><b>View</b></a></td>
            
            <td><a type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteLectureModal" onclick="getID(${user.id})"
                       style="margin-left: 5px;"><b>Delete</b></a></td>
            
            
          </tr>
          </c:forEach>
        </tbody>
      </table>  
</body>
<script>
    //Script used to change the ID hidden input field inside the confirm delete modal
    function getID(value) {

        document.getElementById("deleteId").value = value;
        console.log(document.getElementById("deleteId").value);
    }

</script>
   <%@ include file="DeletePharamacist.jsp" %>
</html>