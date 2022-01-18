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
    <title>Furniture Product List</title>
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
.input{
  width: 500px;
  height: 50px;
  align-content: center;
  border-radius: 55px;
  position: absolute;
  top:15%;
  left: 30%;
  font-size: 1.5rem;
  background: rgb(234, 235, 240);
  background: linear-gradient(135deg, rgb(213, 210, 221) 0%,     rgb(77, 143, 209) 100%);
  padding-left: 1.5rem;
  border: none;
}
.button{
  width: 150px;
  height: 50px;
  position: absolute;
  top:15%;
  right: 26%;
  border-radius: 55px;
  font-size: 1.5rem;
  color: white;
  font-weight: 700;
  background: rgb(234, 235, 240);
  background: linear-gradient(135deg, rgb(122, 97, 180) 0%,     rgb(77, 143, 209) 100%);
  border: 0px;
  cursor: pointer;
  transition: opacity 0.25s ease-out;
}
</style>
<body>
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
<%@include file="Message.jsp" %>
 <%@include file="ViewAllError.jsp" %>
  <%@include file="Error12.jsp" %>
<%@include file="AdvanceSearchItem.jsp" %>
<br>
<br><br><br>

<h1 style="text-align: center;"><b>VIEW ALL ITEMS</b></h1>
    <table class="table table-striped" >
        <thead>
          <tr style="background: rgb(87, 217, 240);
	background: linear-gradient(135deg, rgb(161, 181, 236) 0%,
		rgb(39, 179, 197) 100%);">
          <th scope="col">Item ID</th>
            <th scope="col">Item Name</th>
            <th scope="col">Image</th>
            <th scope="col">Category</th>
            <th scope="col">Availability</th>
            <th scope="col" class="border-left">Update</th>
            <th scope="col" class="border-left">Delete</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
          <tr>
            <td >${item.id}</td>
            <td >${item.name}</td>
            <td ><img class='form-img22'  src=${item.image} alt='image' /></td>
            <td >${item.specifications}</td>
            <td >${item.availability}</td>
            <td class="border-left"><a type="button" class="btn btn btn-warning"  href = "${contextPath}/api/auth/viewItemUpdateByItem/${item.id}"
                       style="margin-left: 5px;"><b>Update</b></a></td>
           
            <td class="border-left"><a type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteItemModal" onclick="getID(${item.id})"
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

<%@ include file="DeleteItem.jsp" %>
</html>