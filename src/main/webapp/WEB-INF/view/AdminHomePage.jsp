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
    <title>DOMSEL Product List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');

*{

    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
.body{

    font-family: 'Poppins', sans-serif;
}
img{
    width: 100%;
    display: block;
}
.main-wrapper{
    background: rgb(87, 217, 240);
	background: linear-gradient(135deg, rgb(161, 181, 236) 0%,
		rgb(39, 179, 197) 100%);
    min-height: 100vh;
    overflow-x: 0;
}
.container{
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 16px;
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
.display-style-btns{
    margin: 10px 0;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    background-color: #fff;
    padding: 16px 0;
    border-radius: 5px;
}
.display-style-btns button{
    border: none;
    font-size: 22px;
    display: inline-block;
    vertical-align: top;
    margin: 0 8px;
    background-color: transparent;
    cursor: pointer;
    transition: all 0.3s ease-out;
}
.display-style-btns button:hover{
    color: #f79410;
}
.active-btn{
    color: #f79410;
}


.item-list{
    margin: 36px 0;
    display: grid;
    grid-template-columns: repeat(1, 1fr);
    row-gap: 32px;
}
.item{
    background: rgb(68, 106, 230);
	background: linear-gradient(135deg, rgb(113, 126, 241) 0%,
		rgb(95, 82, 170) 100%);
    border-radius: 5px;
    overflow: hidden;
    box-shadow: 0 0 4px 0 rgba(15, 4, 4, 0.05);
    transition: all 0.2s ease-out;
    border-color: #202020;
}
.item:hover{
    box-shadow: 0 0 10px 1px rgba(0, 4, 4, 0.15);
}
.item-img{
    position: relative;
    overflow: hidden;
}
.item-img img{
    width: 70%;
    margin: 16px auto;
}
.icon-list{
    position: absolute;
    bottom: -100px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease-out;
}
.icon-list button{
    border: none;
    background-color: #202020;
    color: #fff;
    margin: 0 6px;
    width: 35px;
    height: 35px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 15px;
    cursor: pointer;
    transition: all 0.3s ease-out;
}
.icon-list button:hover{
    background-color: #f4f4f4;
    color: #202020;
}
.item-img:hover .icon-list{
    bottom: 26px;
}
.item-detail{
    padding: 16px;
    color: #202020;
    text-align: center;
}
.item-name{
    font-weight: 500;
    font-size: 18px;
    color: #202020;
    display: block;
    color: #fff; 
}
.item-price{
    margin: 10px 0;
    font-weight: 300;
    display: flex;
    align-items: center;
    justify-content: center;
}
.old-price{
    text-decoration: line-through;
    opacity: 0.6;
}
.new-price{
    color: #f79410;
    font-size: 18px;
    font-weight: 600;
    margin-right: 10px;
}
.item-detail p{
    font-weight: 300;
    opacity: 0.9;
    font-size: 15px;
    line-height: 1.7;
    display: none;
}
.add-btn{
    margin: 16px 0;
    text-transform: uppercase;
    border: none;
    background-color: #202020;
    color: #fff;
    font-family: inherit;
    padding: 10px 28px;
    border: 1px solid #202020;
    cursor: pointer;
    transition: all 0.3s ease-out;
    display: none;
}
.add-btn:hover{
    background-color: #fff;
    color: #202020;
}


/* stylings for details active */
.details-active.item-list{
    grid-template-columns: 100%;
}
.details-active .item{
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    align-items: center;
}
.details-active .item-detail{
    text-align: left;
}
.details-active .item-price{
    justify-content: flex-start;
}
.details-active .item-detail p{
    display: block;
}
.details-active .item-detail .add-btn{
    display: block;
}



@media screen and (min-width: 678px){
    .item-list{
        grid-template-columns: repeat(2, 1fr);
        gap: 32px;
    }
}

@media screen and (min-width: 768px){
    .item-list{
        grid-template-columns: repeat(3, 1fr);
    }
}

@media screen and (max-width: 576px){
    .details-active .item{
        grid-template-columns: 100%;
    }
}
h2 {  
padding: 25px 0;  
text-align: center;  
color: #fff;  
background: #7c8490;  
}  
footer {  
 background: rgb(9, 95, 234);  
  color: white;  
  margin-top:100px;  
}  
footer a {  
  color: #fff;  
  font-size: 14px;  
  transition-duration: 0.2s;  
}  
footer a:hover {  
  color: #FA944B;  
  text-decoration: none;  
}  
.copy {  
  font-size: 12px;  
  padding: 10px;  
  border-top: 1px solid #FFFFFF;  
}  
.footer-middle {  
  padding-top: 2em;  
  color: white;  
}  
ul.social-network {  
  list-style: none;  
  display: inline;  
  margin-left: 0 !important;  
  padding: 0;  
}  
ul.social-network li {  
  display: inline;  
  margin: 0 5px;  
}  
  
.social-network a.icoFacebook:hover {  
  background-color: #3B5998;  
}  
.social-network a.icoLinkedin:hover {  
  background-color: #007bb7;  
}  
.social-network a.icoFacebook:hover i  
{  
  color: #fff;  
}  
.social-network a.icoLinkedin:hover i {  
  color: #fff;  
}  
.social-network a.socialIcon:hover {  
  color: #44BCDD;  
}  
.socialHoverClass {  
  color: #44BCDD;  
}  
.social-circle li a {  
  display: inline-block;  
  position: relative;  
  margin: 0 auto 0 auto;  
  -moz-border-radius: 50%;  
  -webkit-border-radius: 50%;  
  border-radius: 50%;  
  text-align: center;  
  width: 30px;  
  height: 30px;  
  font-size: 15px;  
}  
.social-circle li i {  
  margin: 0;  
  line-height: 30px;  
  text-align: center;  
}  
.social-circle li a:hover i  
{  
  -moz-transform: rotate(360deg);  
  -webkit-transform: rotate(360deg);  
  -ms--transform: rotate(360deg);  
  transform: rotate(360deg);  
  -webkit-transition: all 0.2s;  
  -moz-transition: all 0.2s;  
  -o-transition: all 0.2s;  
  -ms-transition: all 0.2s;  
  transition: all 0.2s;  
}  
.triggeredHover {  
  -moz-transform: rotate(360deg);  
  -webkit-transform: rotate(360deg);  
  -ms--transform: rotate(360deg);  
  transform: rotate(360deg);  
  -webkit-transition: all 0.2s;  
  -moz-transition: all 0.2s;  
  -o-transition: all 0.2s;  
  -ms-transition: all 0.2s;  
  transition: all 0.2s;  
}  
.social-circle i {  
  color: #595959;  
  -webkit-transition: all 0.8s;  
  -moz-transition: all 0.8s;  
  -o-transition: all 0.8s;  
  -ms-transition: all 0.8s;  
  transition: all 0.8s;  
}  
.social-network a {  
  background-color: #F9F9F9;  
}  
.social-network a:hover {  
background: #ff304d;  
} 
</style>
<body style="background: rgb(87, 217, 240);
	background: linear-gradient(135deg, rgb(161, 181, 236) 0%,
		rgb(39, 179, 197) 100%);">
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
    <div class = "main-wrapper">
        <div class = "container">
           
              <security:authorize access="isAuthenticated()" >
     <security:authentication property="principal.username" var="username"/> 
</security:authorize>
<h1 style="text-align: center;
		font-size: 22px; color: #fff; "><b>Hi , ${username}</b></h1>
<h1 id="lblGreetings" style="text-align: center;
		font-size: 22px; color: #fff; "><b></b></h1>
<h1 style="text-align: center;
		font-size: 22px; color: #fff; "><b>Welcome to Admin Page</b></h1>
           
          <br><br>

            <div class = "item-list">
                <div class = "item">
                    <div class = "item-img">
                        <img src = "https://blog.cardsdirect.com/wp-content/uploads/2016/10/The-Customer-Icon-620x490.jpg">
                    </div>
                    <div class = "item-detail">
                        <a href = "${contextPath}/api/auth/viewAllUserPage" class = "item-name" >MANAGE USERS</a>

                    </div>
                </div>

                <div class = "item">
                  <div class = "item-img">
                      <img src = "https://static.vecteezy.com/system/resources/previews/001/110/637/original/cartoon-style-medical-staff-holding-blank-sign-vector.jpg">
                  </div>
                  <div class = "item-detail">
                      <a href = "${contextPath}/api/auth/addItemTable" class = "item-name">MANAGE DRUGS</a>
                      
                      
                  </div>
              </div>

              <div class = "item">
                <div class = "item-img">
                    <img src = "https://static.vecteezy.com/system/resources/previews/000/425/642/original/pharmacy-with-nurse-in-counter-drugstore-cartoon-character-vector.jpg">
                </div>
                <div class = "item-detail">
                    <a href = "${contextPath}/api/auth/viewAllPharmacient" class = "item-name">MANAGE PHARMACISTS</a>
                    
                </div>
            </div>

        </div>
        </div>
    </div>



    <script >const itemList = document.querySelector('.item-list');
      const gridViewBtn = document.getElementById('grid-active-btn');
      const detailsViewBtn = document.getElementById('details-active-btn');
      
      gridViewBtn.classList.add('active-btn');
      
      gridViewBtn.addEventListener('click', () => {
          gridViewBtn.classList.add('active-btn');
          detailsViewBtn.classList.remove('active-btn');
          itemList.classList.remove('details-active');
      });
      
      detailsViewBtn.addEventListener('click', () => {
          detailsViewBtn.classList.add('active-btn');
          gridViewBtn.classList.remove("active-btn");
          itemList.classList.add("details-active");
      });</script>
      <footer class="mainfooter" role="contentinfo">  
  <div class="footer-middle">  
  <div class="container">  
    <div class="row">  
      <div class="col-md-3 col-sm-6">  
        <div class="footer-pad">  
          <h4> Contact Us</h4>  
          <ul class="list-unstyled">  
            <li> <a href="#"> </a> </li>  
            <li> <a href="#">Payment Center</a></li>  
            
          </ul>  
        </div>  
      </div>  
      <div class="col-md-3 col-sm-6">  
        <div class="footer-pad">  
          <h4> About Us</h4>  
          <ul class="list-unstyled">  
            <li><a href="#"> Blog </a> </li>  
            
          </ul>  
        </div>  
      </div>  
     
        <div class="col-md-3">  
            <h4> Follow Us </h4>  
            <ul class="social-network social-circle">  
             <li> <a href="#" class="icoFacebook" title="Facebook"> <i class="fa fa-facebook"> </i> </a> </li>  
             <li> <a href="#" class="icoLinkedin" title="Linkedin"> <i class="fa fa-linkedin"> </i> </a> </li>  
    <li> <a href="#"> <i class="fa fa-youtube" aria-hidden="true"> </i> </a> </li>  
            </ul>               
    </div>  
    </div>  
    <div class="row">  
    <div class="col-md-12 copy">  
    <p class="text-center"> © Copyright 2021 - Company Name.  All rights reserved. </p>  
    </div>  
    </div>  
  </div>  
  </div>  
</footer> 
</body>
</html>