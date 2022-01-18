<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="${contextPath}/CSS/login.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

html {
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: inherit;
}

.column {
  float: left;
  width: 33.3%;
  margin-bottom: 16px;
  padding: 0 8px;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  margin: 8px;
}

.about-section {
  padding: 50px;
  text-align: center;
  background-color: #00bfff;
  color: white;
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

.button:hover {
  background-color: #1f2463;
}

@media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
}

.navbar-brand {
  letter-spacing: 3px;
  color: #ffffff;
}

.navbar-brand:hover {
	color:#ffffff;
}

.navbar-scroll .nav-link,
.navbar-scroll .fa-bars {
  color: #ffffff;
}

.navbar-scrolled .nav-link,
.navbar-scrolled .fa-bars {
  color: #ffffff;
}

.navbar-scrolled {
  background-color: #ffffff;
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
</head>
<body>
<nav class="navbar navbar-expand-lg fixed-top navbar-scroll shadow-0" style="background-color: #000080;">
  <div class="container">
    <a class="navbar-brand" href="#">DOMSEL</a>
    <button class="navbar-toggler ps-0" type="button" data-mdb-toggle="collapse" data-mdb-target="#navbarExample01"
      aria-controls="navbarExample01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="d-flex justify-content-start align-items-center">
        <i class="fas fa-bars"></i>
      </span>
    </button>
    <div class="collapse navbar-collapse" id="navbarExample01">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
       <li class="nav-item active"><a class="nav-link px-3" href="${contextPath}/api/auth/login"><b>Login</b></a>
					</li>
					<li class="nav-item"><a class="nav-link px-3" href="${contextPath}/api/auth/register"><b>Register</b></a>
					</li>
					<li class="nav-item"><a class="nav-link px-3" href="${contextPath}/api/auth/aboutUs"><b>About
								Us</b></a></li>
					<li class="nav-item active"><a class="nav-link px-3" href="${contextPath}/api/auth/AllContactUs"><b>Contact
								Us</b></a></li>
					<li class="nav-item"><a class="nav-link px-3" href="${contextPath}/api/auth/home"><b>Home</b></a>
					</li>
      </ul>

      <ul class="navbar-nav flex-row">
        <li class="nav-item">
          <a class="nav-link pe-3" href="#!">
            <i class="fab fa-youtube"></i>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link px-3" href="#!">
            <i class="fab fa-facebook-f"></i>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link ps-3" href="#!">
            <i class="fab fa-instagram"></i>
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<br></br>
<br></br>
<div class="about-section">
  <h1>About Us </h1>
  <p>DOMSEL Lanka pharmacy is a 501(c)(3) nonprofit organization founded in 1904. We support and work with our members, the state boards of pharmacy, to protect the public health. NABP is governed by its Executive Committee, which consists of four officers and eight members. Our Constitution and Bylaws direct our operations and activities allowing us to follow our guiding principles.

    NABP was initially established to assist the state boards of pharmacy in creating uniform education and licensure standards. Our members consist of the 50 United States state boards of pharmacy, as well as the boards in District of Columbia, Guam, Puerto Rico, the Virgin Islands, 10 Canadian provinces, and The Bahamas.
    
    Today, we help support patient and prescription drug safety, through examinations that assess pharmacist competency, pharmacist licensure transfer and verification services, and various pharmacy accreditation programs. Our membership and staff combine diverse skills and backgrounds, which helps us create innovative programs that meet the public health protection needs of today – with an eye on the future.</p>
  
</div>

<h2 style="text-align:center">Our Team</h2>
<div class="row">
  <div class="column">
    <div class="card">
      <img src="https://ak6.picdn.net/shutterstock/videos/1010216636/thumb/1.jpg" alt="Jane" style="width:100%">
      <div class="container">
        <h2>Ayeshmi Samaradivakara</h2>
        <p class="title">CEO & Founder</p>
        <p>The technical difference between a founder and a CEO is quite simple — a founder is someone who starts or launches a business, and a CEO is someone who takes the company to scale. The CEO role is the highest-ranking executive roles in any organisation.</p>
        <p>ayeshmi177samaraa@gmail.com</p>
        
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <img src="https://wallup.net/wp-content/uploads/2019/10/724765-mood-model-women-woman-models-female-girl-girls-oriental-asian.jpg" alt="Mike" style="width:100%">
      <div class="container">
        <h2>Shaumi Samaradivakara</h2>
        <p class="title">Head Pharmacist</p>
        <p>Community pharmacists could play a key role in the early identification and referral of patients with suspected head and neck cancer (HNC), according to a multi-institute team of researchers.

            The study, now underway, has the potential to improve the rates of early formal cancer assessment, diagnosis, and treatment, the researchers said. It also will explore whether community pharmacies could offer a pathway for people with HNC symptoms to seek further medical help and advice. </p>
        <p>shaumi33samaraa@gmail.com</p>
        
      </div>
    </div>
  </div>
  
  <div class="column">
    <div class="card">
      <img src="https://wallpapercave.com/wp/wp2844964.jpg" alt="John" style="width:100%">
      <div class="container">
        <h2>Sandy Samaradivakara</h2>
        <p class="title">Designer</p>
        <p>As a professional designer, you will often work with other people on a team, and will have to know how to share and delegate work. It's important to maintain a cooperative, rather than competitive attitude towards other designers. This will lead to being able to do more work, faster, and getting better projects.</p>
        <p>sandy18samaraa@gmail.com</p>
        
      </div>
    </div>
  </div>
</div>

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