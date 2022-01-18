<%@ page import="com.example.demo.dto.MessageResponse" %>
<%
MessageResponse message1 = null;
    try{
        message1 = (MessageResponse) request.getAttribute("contactError");
    }
    catch (Exception e){
        e.printStackTrace();
    }

    if (message1 != null){
%>

<style>
  .alert {
    width:440x;
    height: 78px;
    background: rgb(118, 209, 34);
	background: linear-gradient(135deg, rgb(143, 230, 169) 0%,
		rgb(28, 167, 90) 100%);
    color: white;
    border-radius: 25px;
    font-size: 1.3rem;
    text-align: center;
    position: absolute;
    top: 12%;
    left: 75%;
   
  }
  
  .closebtn {
    margin-left: 20px;
    color: white;
    font-weight: bold;
    float: right;
    font-size: 22px;
    line-height: 20px;
    cursor: pointer;
    transition: 0.3s;
  }
  
  .closebtn:hover {
    color: black;
  }
  .button12{
    border-radius: 25px;
  width: 20%;
  height: 20px;
  font-size: 1.2rem;
  color: white;
  font-weight: 700;
  background: rgb(234, 235, 240);
  background: linear-gradient(135deg, rgb(78, 98, 121) 0%,     rgb(66, 170, 173) 100%);
  border: 0px;
  cursor: pointer;
  transition: opacity 0.25s ease-out;
  }
  </style>
<div class="alert" style="align-content: center;">
  
  <strong > <%= message1.getMessage() %></strong> 
 
 <form action="${contextPath}/api/auth/contactUs"  id="myForm">
  
                        <input hidden id="deleteId" name="userId">
                        <button type="submit" class="button12" aria-label="Close">Ok</button>
                    </form>
</div>

<% } %>