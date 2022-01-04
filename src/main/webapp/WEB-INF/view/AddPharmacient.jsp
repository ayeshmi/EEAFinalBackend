<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<html>
<style>
.form12 {
	background: rgb(76, 26, 194);
	background: linear-gradient(135deg, rgb(71, 169, 194) 0%,
		rgb(19, 21, 131) 100%);
	width: 750px;
	border-radius: 55px;
	margin: 80px auto;
	height: 900px;
}

#headerTitle1 {
	text-align: center;
	font-family: 'open sans', sans-serif;
	padding: 0rem 0;
	margin: 0;
	font-size: 2.0rem;
}

.row1 label {
    color: white;
	align-self: start;
	padding-left: 7.5rem;
	padding-bottom: 0.5rem;

}

.row1 {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding-top: 1rem;
	max-width: 100%;
	min-width: 100px;
}

.row1 input {
	width: 500px;
	height: 50px;
	box-sizing: border-box;
	border: none;
	font-size: 17px;
	padding-left: 1.5rem;
	padding-bottom: 2rem;
	box-shadow: inset 0px -3px 0px 0px rgba(187, 187, 187, 0.2);
	transition: box-shadow 0.2s ease-in;
}

.row1 input:focus {
	box-shadow: inset 0px -3px 0px 0px rgba(34, 193, 195, 0.7);
	outline: none;
}

.row1 input::-webkit-input-placeholder {
	opacity: 1;
	transition: opacity 0.25s ease-out;
}

.row1 input:hover::-webkit-input-placeholder, .row1 input:focus::-webkit-input-placeholder
	{
	opacity: 0;
}

.row1 select {
	width: 500px;
	height: 100px;
	box-sizing: border-box;
	border: none;
	font-size: 17px;
	padding-left: 1.5rem;
	padding-bottom: 2rem;
	box-shadow: inset 0px -3px 0px 0px rgba(187, 187, 187, 0.2);
	transition: box-shadow 0.2s ease-in;
}

.row1 select:focus {
	box-shadow: inset 0px -3px 0px 0px rgba(34, 193, 195, 0.7);
	outline: none;
}

.row1 select::-webkit-input-placeholder {
	opacity: 1;
	transition: opacity 0.25s ease-out;
}

.row1 select:hover::-webkit-input-placeholder, .row1 select:focus::-webkit-input-placeholder
	{
	opacity: 0;
}

.row1 textarea {
	width: 500px;
	height: 100px;
	box-sizing: border-box;
	border: none;
	font-size: 17px;
	padding-left: 1.5rem;
	padding-bottom: 8rem;
	box-shadow: inset 0px -3px 0px 0px rgba(187, 187, 187, 0.2);
	transition: box-shadow 0.2s ease-in;
}

.row1 textarea:focus {
	box-shadow: inset 0px -3px 0px 0px rgba(34, 193, 195, 0.7);
	outline: none;
}

.row1 textarea::-webkit-input-placeholder {
	opacity: 1;
	transition: opacity 0.25s ease-out;
}

.row1 textarea:hover::-webkit-input-placeholder, .row1 textarea:focus::-webkit-input-placeholder
	{
	opacity: 0;
}

.form2 {
	max-width: 800px;
	min-width: 295px;
	height: 715px;
	top: 7%;
	left: 50%;
	background-color: #FFFFFF;
	border-radius: 55px;
	margin: 80px auto;
	position: absolute;
}

label {
	display: flex;
	flex-direction: column;
	align-items: left;
	padding-top: 2rem;
	max-width: 100%;
	color: white;
	align-self: start;
	padding-left: 4.5rem;
	padding-bottom: 0.5rem;
	color: rgba(14, 6, 6, 0.9);
}

.row1 button {
	border-radius: 25px;
	width: 50%;
	height: 50px;
	font-size: 1.3rem;
	color: white;
	font-weight: 700;
	background: rgb(234, 235, 240);
	background: linear-gradient(135deg, rgb(122, 97, 180) 0%,
		rgb(77, 143, 209) 100%);
	border: 0px;
	cursor: pointer;
	transition: opacity 0.25s ease-out;
}

.form-img22 {
	width: 200px;
	height: 200px;
}
</style>
<body>
	<jsp:include page="Navbar.jsp">
		<jsp:param name="page2" value="home2" />
	</jsp:include>
	<%@include file="ViewAllError.jsp" %>
	<form class="row1 form12" id="formElem" action="/api/auth/addPharmacient"
							method="POST" enctype="multipart/form-data">
							<h2>Add Pharmacist</h2>
<!-- Email input -->
							<div class="form-outline mb-4">
							<label
									class="form-label" for="form1Example13">First Name</label>
								<input type="text" id="form1Example13"
									class="form-control form-control-lg" name="firstName" placeholder="Enter first name" required/> 
							</div>

							<!-- Email input -->
							<div class="form-outline mb-4">
							<label
									class="form-label" for="form1Example13">Last Name</label>
							
								<input type="text" id="form1Example13"
									class="form-control form-control-lg" name="lastName" placeholder="Enter last name" required/> 
							</div>
							<!-- Password input -->
							<div class="form-outline mb-4">
							<label
									class="form-label" for="form1Example23">Contact Number</label>
								<input type="text" id="form1Example23"
									class="form-control form-control-lg" name="contactNumber" placeholder="Enter contact number" required/> 
							</div>
							
							<div class="form-outline mb-4">
							<label
									class="form-label" for="form1Example23">Email</label>
								<input type="text" id="form1Example23"
									class="form-control form-control-lg" name="email" placeholder="Enter email" required/> 
							</div>
							
							<div class="form-outline mb-4">
							<label
									class="form-label" for="form1Example23">Address</label>
								<input type="text" id="form1Example23"
									class="form-control form-control-lg" name="address" placeholder="Enter address" required/> 
							</div>
							
							<label class="form-label" for="inputAddress">Pharmacist image</label>
          <input type="hidden" name="role" value="pharmacist" />
              <input type="file" name="image" 
                id="customFile" value="Click the above button to upload the image" style="height: 50px;" required>
<br></br>
							<!-- Submit button -->
							<button type="submit" class="button">Submit</button>




						</form>

</body>

</html>