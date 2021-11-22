<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>

<h1>The Storage getItem() Method</h1>

<p>This example demonstrates how to use the getItem() method to get the value of a specified local storage item.</p>

<h2>Missing localStorage items?</h2>

<p>Since you might not have the specified item stored in your local storage, we have added a script that creates it for you.</p>

<button onclick="createItem()">Create local storage item</button>


<br></br>
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
<h2>Get the value of the local storage item named "mytime"</h2>

<p>Click the button to get the item value:</p>
<security:authorize access="isAuthenticated()" >
    authenticated as <security:authentication property="principal.email" /> 
</security:authorize>
<button onclick="myFunction()">Get the item value</button>
<security:authorize access="hasAuthority('ROLE_USER')">
<p id="currentUser"></p>
<c:forEach var="lecturer" items="${user}">

   


            <tr class="table-secondary">

                <td>${lecturer.username} </td>
                <td>${lecturer.email}</td>
                

                
            </tr>
           
        </c:forEach>
        </security:authorize>
<script>

function createItem() {
  localStorage.mytime = Date.now();
}

function myFunction() {
  var x = localStorage.getItem("mytime");
  document.getElementById("demo").innerHTML = x;
}

</script>
</body>
</html>