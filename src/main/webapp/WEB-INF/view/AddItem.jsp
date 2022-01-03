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
	height: 1450px;
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
	height: 100px;
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
	height: 100px;
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
	<form class="row1 form12" action="/api/auth/addItem" method="post"
		enctype="multipart/form-data" id="ayeshmi">
		<h1 id="headerTitle1">
			<b>Add New Item.</b>
		</h1>
		<br></br> <label for="inputAddress">Item name</label> <input
			type="text" class="form-control" id="inputAddress"
			placeholder="Enter item name" name="name" required> <label
			for="inputAddress">Item Type</label> <select name="itemType"
			id="pet-select" >
			<option value="">--Please choose an option--</option>
			<option value="Drug">Drug</option>
			<option value="Equipment">Equipment</option>


		</select> <label for="inputAddress">Price (Rs.)</label> <input type="number"
			class="form-control" id="inputAddress" placeholder="Enter item price"
			name="price" required> <label for="inputAddress">Description</label>
		<textarea rows="5" cols="50" name="description"
			class="form-control form-control-lg"
			placeholder="Enter item description" required></textarea>

		<label for="inputAddress">Category</label> <select
			name="specifications" id="pet-select" required>
			<option value="">--Please choose an option--</option>
			<option value="WomanHealth">Woman Health</option>
			<option value="AnimalCare">Animal Care</option>
			<option value="FirstAid">First Aid</option>
			<option value="EyeCare">Eye Care</option>
			<option value="BabyCare">Baby Care</option>
			<option value="SkinCare">Skin Care</option>
			<option value="HairCare">Hair Care</option>
			<option value="SmokeCessation">Smoke Cessation</option>
			<option value="SexualLife">Sexual Life</option>
			<option value="Cosmetics">Cosmetics</option>
			<option value="SupportandBandages">Support and Bandages</option>
			<option value="Beauty">Beauty</option>
			<option value="EarCare">Ear care</option>
			<option value="Feverandpainrelief">Fever and pain relief</option>

		</select> <label for="inputAddress">Suitable for</label>
		<textarea rows="5" cols="60" name="suitableFor"
			class="form-control form-control-lg"
			placeholder="Enter suitable persons" required></textarea>

		<label for="inputAddress">How to use</label>
		<textarea rows="5" cols="10" name="howToUse"
			class="form-control form-control-lg"
			placeholder="Enter how to use this item" required></textarea>


		<label for="inputAddress">Ingredients</label>
		<textarea rows="5" cols="10" name="ingredients"
			class="form-control form-control-lg"
			placeholder="Enter item ingredients" required></textarea>

		<label for="inputAddress">Delivery</label> <select name="delivery"
			id="pet-select" required>
			<option value="">--Please choose an option--</option>
			<option value="Yes">Yes</option>
			<option value="No">No</option>
		</select> <label for="inputAddress">Return</label> <select name="returnItem"
			id="pet-select" required>
			<option value="">--Please choose an option--</option>
			<option value="Yes">Yes</option>
			<option value="No">No</option>
		</select> <label for="inputAddress">Item image</label> <input type="file"
			name="image" id="customFile"
			value="Click the above button to upload the image"
			style="height: 100px;" required> <br></br>
		<button type="file">Submit</button>
		<br></br>
	</form>
	<p>${message}</p>
	<script type="text/javascript">
		function SubForm() {
			$.ajax({
				url : 'http://localhost:8080/api/auth/addItemPage',
				type : 'post',
				data : $('#ayeshmi').serialize(),
				success : function() {
					alert("worked");
				}
			});
		}
	</script>
</body>

</html>