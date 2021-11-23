 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<html>
    <style>
        .form12{
background: rgb(103, 173, 57);
width:750px;
border-radius: 55px;
  margin: 80px auto;
  height:1450px;
}
#headerTitle1{
  text-align: center;
  font-family: 'open sans', sans-serif;
  padding: 0rem 0;
  margin: 0;
  font-size: 2.0rem;
}
.row1 label{
  align-self: start;
  padding-left: 7.5rem;
  padding-bottom: 0.5rem;
  color: rgba(17, 11, 11, 0.9);
}
.row1{
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 1rem;
  max-width: 100%;
  min-width: 100px;
  
}
.row1 input{
  width: 500px;
  height:100px;
  box-sizing: border-box;
  border: none;
  font-size: 17px;
  padding-left: 1.5rem;
  padding-bottom: 2rem;
  box-shadow: inset 0px -3px 0px 0px rgba(187,187,187,0.2);
  transition: box-shadow 0.2s ease-in;
}

.row1 input:focus{
   box-shadow: inset 0px -3px 0px 0px rgba(34,193,195,0.7);
   outline: none;
}

.row1 input::-webkit-input-placeholder{
  opacity: 1;
  transition: opacity 0.25s ease-out;
}

.row1 input:hover::-webkit-input-placeholder,
.row1 input:focus::-webkit-input-placeholder{
  opacity: 0;
}

.row1 select{
  width: 500px;
  height:100px;
  box-sizing: border-box;
  border: none;
  font-size: 17px;
  padding-left: 1.5rem;
  padding-bottom: 2rem;
  box-shadow: inset 0px -3px 0px 0px rgba(187,187,187,0.2);
  transition: box-shadow 0.2s ease-in;
}

.row1 select:focus{
   box-shadow: inset 0px -3px 0px 0px rgba(34,193,195,0.7);
   outline: none;
}

.row1 select::-webkit-input-placeholder{
  opacity: 1;
  transition: opacity 0.25s ease-out;
}

.row1 select:hover::-webkit-input-placeholder,
.row1 select:focus::-webkit-input-placeholder{
  opacity: 0;
}

.row1 textarea{
  width: 500px;
  height:100px;
  box-sizing: border-box;
  border: none;
  font-size: 17px;
  padding-left: 1.5rem;
  padding-bottom: 8rem;
  box-shadow: inset 0px -3px 0px 0px rgba(187,187,187,0.2);
  transition: box-shadow 0.2s ease-in;
}

.row1 textarea:focus{
   box-shadow: inset 0px -3px 0px 0px rgba(34,193,195,0.7);
   outline: none;
}

.row1 textarea::-webkit-input-placeholder{
  opacity: 1;
  transition: opacity 0.25s ease-out;
}

.row1 textarea:hover::-webkit-input-placeholder,
.row1 textarea:focus::-webkit-input-placeholder{
  opacity: 0;
}

.form2{
  max-width: 800px;
  min-width: 295px;
  height: 715px;
  
  top:7%;
  left: 50%;
  background-color: #FFFFFF;
  border-radius: 55px;
  margin: 80px auto;
  position: absolute;
}
label{
  display: flex;
  flex-direction: column;
  align-items: left;
  padding-top: 2rem;
  max-width: 100%;
  align-self: start;
  padding-left: 4.5rem;
  padding-bottom: 0.5rem;
  color: rgba(14, 6, 6, 0.9);
}
.row1 button{
  border-radius: 25px;
  width: 50%;
  height: 80px;
  font-size: 1.3rem;
  color: white;
  font-weight: 700;
  background: rgb(234, 235, 240);
  background: linear-gradient(135deg, rgb(122, 97, 180) 0%,     rgb(77, 143, 209) 100%);
  border: 0px;
  cursor: pointer;
  transition: opacity 0.25s ease-out;
}
.form-img22{
    width: 200px;
    height: 200px;
}
img{
width:100px;
height:100px;
}
    </style>
    <body>
       <jsp:include page="Navbar.jsp">
       <jsp:param name="page2" value="home2"/>
       </jsp:include> 
      <form class="row1 form12" action="/api/auth/" method="post" enctype="multipart/form-data" >
        <h1 id="headerTitle1"><b>View Pharmacient.</b></h1>
        <br></br>
        <img src = ${pharmacient.image}>
            <label for="inputAddress">User name</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Enter item name"  name="name" value="${pharmacient.firstName}" disabled>
          
            <label for="inputAddress">Email</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Enter item price" name="price" value="${pharmacient.lastName}" disabled>

            <label for="inputAddress">Address</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Enter item price"  value="${pharmacient.email}" disabled>
             

            <label for="inputAddress">Address</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Enter item price"  value="${pharmacient.address}" disabled>
            
            <label for="inputAddress">Contact Number</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Enter item price"  value="${pharmacient.contactNumber}" disabled>
          
      </form>
       
    </body>
    
</html>