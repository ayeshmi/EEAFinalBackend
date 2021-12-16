<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
.main-title{
    text-align: center;
}
.main-title h1{
    padding: 16px 0;
    font-weight: 500;
    text-transform: uppercase;
    letter-spacing: 2px;
}
</style>
<body>
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
<%@include file="ViewAllError.jsp" %>
  
  <div class="container">
    
    <section class="h-100 gradient-custom">
      <div class="container py-5">
      <div class = "main-title">
                <h1>Cart Details</h1>
            </div>
        <div class="row d-flex justify-content-center my-4">
          <div class="col-md-8">
          
          
          <c:forEach var="cart" items="${orders}">
                  
            <div class="card mb-4">
              <div class="card-header py-3">
                <h5 class="mb-0">Cart items</h5>
              </div>
            
              <div class="card-body">
                <!-- Single item -->
                <div class="row">
                
                  <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
                    <!-- Image -->
                     
                    <div class="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">
                                        
                     
                      <img src=${cart.itemImage}
                        class="w-100" />
                      <a href="#!">
                        <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                      </a>
                    </div>
                    <!-- Image -->
                  </div>
    
                  <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                    <!-- Data -->
                   <input type="hidden" name="id" value=${item.id} /> 
                    <p><strong></strong></p>
                    <a href="${contextPath}/api/auth/viewItemByItem/${cart.itemId}">${cart.name}</a>
                   <sec:authorize access="isAuthenticated()" >
     <sec:authentication property="principal.id" var="id"/> 
</sec:authorize>
                    <p>Order By:${cart.clientEmail}</p>
                    <a type="button" class="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip" href="${contextPath}/api/auth/deletecartItem/${cart.id}/${id}"
                      title="Remove item">
                      <i class="fas fa-trash"></i>
                      
                    </a>
                    
                    <!-- Data -->
                  </div>
                  
   
                  <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                    <!-- Quantity -->
                    <div class="d-flex mb-4" style="min-width: 150px">
                      
    
                      <div class="form-outline">
                        <input id="form1" min="0" name="quantity" value=${cart.quantity} type="number" class="form-control" disabled/>
                        <label class="form-label" for="form1">Quantity</label>
                      </div>
    
                     
                    </div>
                    <!-- Quantity -->
    
                    <!-- Price -->
                    <p class="text-start text-md-center">
                      <strong>Rs.${cart.totalPrice}</strong>
                    </p>
                    <!-- Price -->
                  </div>
                    
                </div>
                   
                <!-- Single item -->
    
                <hr class="my-4" />
    
              </div>
            </div>
         
           
             </c:forEach>
            <div class="card mb-4">
              
                     
            </div>
            <div class="card mb-4 mb-lg-0">
              <div class="card-body">
                <p><strong>We accept</strong></p>
                <img class="me-2" width="45px"
                  src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                  alt="Visa" />
                <img class="me-2" width="45px"
                  src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
                  alt="American Express" />
                <img class="me-2" width="45px"
                  src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
                  alt="Mastercard" />
                <img class="me-2" width="45px"
                  src="https://mdbootstrap.com/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.png"
                  alt="PayPal acceptance mark" />
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4">
              <div class="card-header py-3">
                <h5 class="mb-0">Summary</h5>
              </div>
              <div class="card-body">
                <ul class="list-group list-group-flush">
                
                 
                  <li
                    class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                    <div>
                      <strong>Total amount</strong>
                      <strong>
                        <p class="mb-0">(including VAT)</p>
                      </strong>
                    </div>
                    <span><strong>${totalPrice}.00</strong></span>
                  </li>
                </ul>
    
                <a type="button" class="btn btn-primary btn-lg btn-block" href = "${contextPath}/api/auth/orderConfirmation/${totalPrice}">
                  Go to checkout
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</body>
</html>
