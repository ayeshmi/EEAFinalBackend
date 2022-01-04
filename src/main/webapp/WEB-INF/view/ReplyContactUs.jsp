 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<html>
    <style>
        .form12{
background: rgb(76, 26, 194);
	background: linear-gradient(135deg, rgb(71, 169, 194) 0%,
		rgb(19, 21, 131) 100%);
width:750px;
border-radius: 55px;
  margin: 80px auto;
  height:950px;
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

.row1 textarea {
	width: 500px;
	height: 75px;
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
	width: 300px;
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

    </style>
    <body>
       <jsp:include page="Navbar.jsp">
       <jsp:param name="page2" value="home2"/>
       </jsp:include> 
      <form class="row1 form12" action="/api/auth/contactus/${contactUs.id}" method="post"  >
        <h1 id="headerTitle1"><b>Reply ContactUs Question.</b></h1>
        <br></br>
        
            <label for="inputAddress">User name</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Enter item name"  name="name" value="${contactUs.name}" disabled>
          
            <label for="inputAddress">Email</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Enter item price" name="email" value="${contactUs.email}" disabled>

            <label for="inputAddress">Message</label>
            <textarea rows = "5" cols = "50" name = "message"  class="form-control form-control-lg" placeholder="Enter your answer" disabled>${contactUs.message}</textarea>

            <label for="inputAddress">Answer</label>
            <textarea rows = "5" cols = "50" name = "answer"  class="form-control form-control-lg" placeholder="Enter your answer" required></textarea>
            <br></br>
            <button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
          
      </form>
       
    </body>
    
</html>