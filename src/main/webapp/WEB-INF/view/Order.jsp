<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
  <div class="container">
    <div class = "main-title">
                <h1>CheckOut</h1>
            </div>
    <section class="h-100 h-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col">
          <div class="card">
            <div class="card-body p-4">
  
              <div class="row">
    <form class="mt-4" action="/api/auth/addPayment"
							method="POST">
                <div class="col-lg-7">

                  
                    <h5 class="mb-0">Customer details</h5>
                    <br></br>
                  
                    <div class="form-outline form-white mb-4">
                      <input type="text" id="typeName" class="form-control form-control-lg" siez="17"
                        placeholder="Customer Name" />
                      <label class="form-label" for="typeName">Customer Name</label>
                    </div>
                    <div class="form-outline form-white mb-4">
                      <input type="text" id="typeName" class="form-control form-control-lg" siez="17"
                        placeholder="Contact Number" />
                      <label class="form-label" for="typeName">Contact Number</label>
                    </div>
                    <div class="form-outline form-white mb-4">
                      <input type="text" id="typeName" class="form-control form-control-lg" siez="17"
                        placeholder="Address" />
                      <label class="form-label" for="typeName">Address</label>
                    </div>
                </div>
                <div class="col-lg-5">
  
                  <div class="card bg-primary text-white rounded-3">
                    <div class="card-body">
                      <div class="d-flex justify-content-between align-items-center mb-4">
                        <h5 class="mb-0">Card details</h5>
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-6.jpg"
                          class="img-fluid rounded-3" style="width: 45px;" alt="Avatar">
                      </div>
  
                      <p class="small mb-2">Card type</p>
                      <a href="#!" type="submit" class="text-white"><i
                          class="fab fa-cc-mastercard fa-2x me-2"></i></a>
                      <a href="#!" type="submit" class="text-white"><i
                          class="fab fa-cc-visa fa-2x me-2"></i></a>
                      <a href="#!" type="submit" class="text-white"><i
                          class="fab fa-cc-amex fa-2x me-2"></i></a>
                      <a href="#!" type="submit" class="text-white"><i class="fab fa-cc-paypal fa-2x"></i></a>
  
                      
                        <div class="form-outline form-white mb-4">
                          <input type="text" id="typeName" class="form-control form-control-lg" siez="17"
                            placeholder="Cardholder's Name" name="cardHolderName"/>
                          <label class="form-label" for="typeName">Cardholder's Name</label>
                        </div>
  
                        <div class="form-outline form-white mb-4">
                          <input type="text" id="typeText" class="form-control form-control-lg" siez="17"
                            placeholder="1234 5678 9012 3457" minlength="19" maxlength="19" name="cardNumber"/>
                          <label class="form-label" for="typeText">Card Number</label>
                        </div>
  
                        <div class="row mb-4">
                          <div class="col-md-6">
                            <div class="form-outline form-white">
                              <input type="text" id="typeExp" class="form-control form-control-lg"
                                placeholder="MM/YYYY" size="7" id="exp" minlength="7" maxlength="7" name="expiration"/>
                              <label class="form-label" for="typeExp">Expiration</label>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-outline form-white">
                              <input type="password" id="typeText" class="form-control form-control-lg"
                                placeholder="&#9679;&#9679;&#9679;" size="1" minlength="3" maxlength="3" name="cvv"/>
                              <label class="form-label" for="typeText">Cvv</label>
                            </div>
                          </div>
                        </div>
  
                     
  
                      <hr class="my-4">
  
                      <div class="d-flex justify-content-between">
                          <p class="mb-2">Subtotal</p>
                          <p class="mb-2" name="totalPrice">Rs.${totalPrice }.00</p>
                        </div>
    
                        <div class="d-flex justify-content-between">
                          <p class="mb-2">Delivery Fee</p>
                          <p class="mb-2">Rs.${deliveryFee }.00</p>
                        </div>
    
                        <div class="d-flex justify-content-between mb-4">
                          <p class="mb-2">Total(with Delivery Fee)</p>
                          <p class="mb-2">Rs.${totalFee }.00</p>
                        </div>
    <security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.email" var="email"/> 
</security:authorize>
    
    <button type="button" class="btn btn-primary btn-lg btn-block">
                  <div class="d-flex justify-content-between">
                            <span>${totalFee }</span>
                            <span>Order <i class="fas fa-long-arrow-alt-right ms-2"></i></span>
                          </div>
                </button>
                        
                    </div>
                  </div>
  
                </div>
  </form>
              </div>
            
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  
  </div>
</body>
</html>