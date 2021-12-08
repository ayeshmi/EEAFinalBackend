<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--Code by Divinector (www.divinectorweb.com)-->
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Responsive Homepage design with Bootstrap and Animate css</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;900&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.6.2/animate.min.css" rel="stylesheet">
	
	
    <link rel="stylesheet" href="style.css"> 
	   

</head>
<style>
	body {
	font-family: 'Poppins', sans-serif;
}
header .carousel-inner .item {
	height: 100vh;
}
.navbar-inverse {
	background-color: transparent;
	border-color: transparent;
}
.navbar-inverse .navbar-brand {
	color: #00bfff;
	font-size: 40px;
	padding: 40px 15px;
	font-weight: 900;
}
.nav.navbar-nav.navbar-right {
	
	margin: 25px 0;
}
.navbar-inverse .navbar-nav>li>a {
	color: rgb(17, 9, 9);
	text-transform: uppercase;
	font-size: 25px;
}
.banner {
	-webkit-background-size: cover;
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
	height: 100vh;
}
.carousel-caption {
	padding-bottom: 250px;
	font-family: poppins;
}
.carousel-caption h2 {
	font-size: 70px;
	text-transform: uppercase;
	font-weight: bold;
}
.carousel-caption h2 span {
	color: #00bfff;
}
.carousel-caption a {
	background: #00bfff;
	padding: 15px 35px;
	display: inline-block;
	margin-top: 15px;
	color: #fff;
	text-transform: uppercase;
	border-radius: 25px;
}
.carousel-control.right {
	background-image: none;
}
.carousel-control.left {
	background-image: none;
}
.carousel-indicators .active {
	background-color: #00bfff;
	border-color: #00bfff;
}
/*responsive css*/

@media only screen and (min-width: 768px) and (max-width: 991px) {
	.carousel-caption {
		padding-bottom: 350px;
	}
	.carousel-caption h2 {
		font-size: 50px;
	}
}
@media only screen and (max-width: 767px) {
	.navbar-inverse .navbar-brand {
		font-size: 30px;
		padding: 20px 15px;
		background-color: #00bfff;
	}
	.navbar-collapse {
		background: rgba(0, 0, 0, 0.5);
	}
	.carousel-caption {
		padding-bottom: 120px;
	}
	.carousel-caption h2 {
		font-size: 25px;
	}
	.carousel-caption h3 {
		font-size: 18px;
	}
	.carousel-caption a {
		padding: 10px 25px;
	}
}
</style>
<body>

    <header>
        <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">DOMSEL</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><b>Home</b></a></li>
                        <li><a href="${contextPath}/api/auth/contactUs"><b>Contact Us</b></a></li>
                        <li><a href="${contextPath}/api/auth/login"><b>About Us</b></a></li>
                        <li><a href="${contextPath}/api/auth/login"><b>Login</b></a></li>
                        <li><a href="${contextPath}/api/auth/register"><b>Register</b></a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>


        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <div class="banner" style="background-image: url(https://wwwassets.rand.org/content/rand/pubs/technical_reports/TR686/jcr:content/par/teaser.aspectfit.0x1200.jpg/1395180152458.jpg);"></div>
                    <div class="carousel-caption">
                        <h2 class="animated bounceInRight" style="animation-delay: 1s">We Have <span>Good Medicines</span></h2>
                        <h3 class="animated bounceInLeft" style="animation-delay: 2s">DOMSEL Pharmacy Management System</h3>
                        <p class="animated bounceInRight" style="animation-delay: 3s"><a href="${contextPath}/api/auth/login">GET START</a></p>
                    </div>
                </div>
                <div class="item">
                    <div class="banner" style="background-image: url(https://info.nhanow.com/hubfs/giving-customer-subscription.jpg);"></div>
                    <div class="carousel-caption">
                        <h2 class="animated slideInDown" style="animation-delay: 1s">We Have <span>Good Service</span></h2>
                        <h3 class="animated fadeInUp" style="animation-delay: 2s">DOMSEL Pharmacy Management System</h3>
                        <p class="animated zoomIn" style="animation-delay: 3s"><a href="${contextPath}/api/auth/login">GET START</a></p>
                    </div>
                </div>
                <div class="item">
                    <div class="banner" style="background-image: url(https://assets.entrepreneur.com/content/3x2/2000/20160427194746-customer-service-online-shopping-ecommerce-trust.jpeg);"></div>
                    <div class="carousel-caption">
                        <h2 class="animated zoomIn" style="animation-delay: 1s">We Have <span>Customers</span></h2>
                        <h3 class="animated fadeInLeft" style="animation-delay: 2s">DOMSEL Pharmacy Management System</h3>
                        <p class="animated zoomIn" style="animation-delay: 3s"><a href="${contextPath}/api/auth/login">GET START</a></p>
                    </div>
                </div>

            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

    </header>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>


</html>

