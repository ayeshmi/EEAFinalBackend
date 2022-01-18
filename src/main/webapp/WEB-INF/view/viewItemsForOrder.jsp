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
 border-radius: 25px;
	width: 400px;
	height: 50px;
	font-size: 1.3rem;
	align-content:center;
	color: white;
	font-weight: 700;
	background: rgb(234, 235, 240);
	background: linear-gradient(135deg, rgb(122, 97, 180) 0%,
		rgb(77, 143, 209) 100%);
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
<br></br>
 <%@include file="Message.jsp"%>
	<%@include file="ErrorMessage.jsp"%>
	<%@include file="ViewAllError.jsp"%>

<h1 style="text-align: center;"><b>Order Items</b></h1>

    <table class="table table-striped">
        <thead>
          <tr style="background: rgb(87, 217, 240);
	background: linear-gradient(135deg, rgb(161, 181, 236) 0%,
		rgb(39, 179, 197) 100%);">
            <th scope="col">Item Name</th>
            <th scope="col">Item Image</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price (Rs.)</th>
            <th scope="col">Confirm</th>
        
          </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${orders}">
          <tr>
             <td>${user.name}</td>
             <td><img class='form-img22'  src=${user.itemImage} alt='image' /></td>
             <td>${user.quantity}</td>
             <td>${user.price}</td>
<td class="border-left"><a type="button" class="btn btn btn-warning"  href = "${contextPath}/api/auth/pharmacistConfirmation/${user.id}/${user.date}"
                       style="margin-left: 5px;"><b>Completed</b></a></td>
            
            
            
          </tr>
          </c:forEach>
        </tbody>
      </table>
        
     
</body>


</html>