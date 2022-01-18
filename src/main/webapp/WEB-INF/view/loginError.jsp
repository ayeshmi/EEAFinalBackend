<%@ page import="com.example.demo.dto.MessageResponse" %>
<%
MessageResponse message = null;
    try{
        message = (MessageResponse) request.getAttribute("error");
    }
    catch (Exception e){
        e.printStackTrace();
    }

    if (message != null){
%>

<style>
  .alert {
   width:450px;
    height: 75px;
    background: rgb(209, 34, 34);
	background: linear-gradient(135deg, rgb(241, 142, 159) 0%,
		rgb(238, 8, 8) 100%);
    color: white;
    border-radius: 25px;
    font-size: 1.0rem;
    text-align: center;
    position: absolute;
    top: 9%;
    left: 70%;
   
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
  font-size: 0.9rem;
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
  
  <strong > <%= message.getMessage() %></strong> 
 
 <br>
                        <button onclick="this.parentElement.style.display='none';" class="button12" >Ok</button>
                    
</div>
 

<% } %>