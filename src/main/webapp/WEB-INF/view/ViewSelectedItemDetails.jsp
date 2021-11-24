 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
   @media (min-width: 1025px) {
.h-custom {
height: 100vh !important;
}
}

.card-registration .select-input.form-control[readonly]:not([disabled]) {
font-size: 1rem;
line-height: 2.15;
padding-left: .75em;
padding-right: .75em;
}

.card-registration .select-arrow {
top: 13px;
}

.bg-grey {
background-color: #eae8e8;
}

@media (min-width: 992px) {
.card-registration-2 .bg-grey {
border-top-right-radius: 16px;
border-bottom-right-radius: 16px;
}
}

@media (max-width: 991px) {
.card-registration-2 .bg-grey {
border-bottom-left-radius: 16px;
border-bottom-right-radius: 16px;
}
}
</style>
<body>
<jsp:include page="Navbar.jsp">
       <jsp:param name="page2" value="home2"/>
       </jsp:include> 
  <div class="container">
    <h1>${item.name}</h1>
    <br></br>
    <div class="row">
     
      <div class="col">
        
        <img src=${item.image} alt="Girl in a jacket" width="200" height="200">

      </div>
      <div class="col">
        
        <p>${item.description}</p>
        <p>${item.id}</p>
        <p>Availability: ${item.availability}</p>
        
         <form action="/api/auth/addToCart/${item.id}" method="post">
          <div class="d-flex mb-4" style="max-width: 300px">

          <img src="https://static.vecteezy.com/system/resources/previews/000/449/817/original/vector-add-to-cart-vector-icon.jpg" alt="Girl in a jacket" width="50" height="50">
          <div class="form-outline">
            
            <input id="form1" min="0" name="quantity" value="1" type="number" class="form-control" style="width:100px;height: 50px;"/>
            
          </div>

         <button class="btn btn-primary px-3 ms-2" style="margin-left: 30px" onclick="${contextPath}/api/auth/profile " >Add to cart</button>  
          </div>
          
        
         
          <security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.email" var="clientEmail"/> 
</security:authorize>
<security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.id" var="userId"/> 
</security:authorize>

<input type="hidden" name="clientEmail" value=${clientEmail} />
<input type="hidden" name="userId" value=${userId} />
<input type="hidden" name="price" value=${item.price} />

       
       
       </form>
          
            </div> 
          
           
       
        
      </div>
      

    </div>
    <div class="row">
      <div class="col">
        <h2>Suitable For:</h2>
        <p>${item.suitableFor}</p>
        
      </div>
      <div class="col">
        <h2>How To Use:</h2>
        <p>${item.howToUse}</p>
        
      </div>
      <div class="col">
        <h2>Ingredients:</h2>
        <p>${item.ingredients}</p>
        
      </div>
    </div>

    <div class="row">
      
      <div class="col">
        <h2>Delivery:</h2>
        <p>${item.delivery}</p>
        
      </div>
      <div class="col">
        <h2>Return:</h2>
        <p>${item.returnItem}</p>
        
      </div>
    </div>

</body>
</html>