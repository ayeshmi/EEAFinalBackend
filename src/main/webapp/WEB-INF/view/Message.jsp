<%@ page import="com.example.demo.dto.MessageResponse" %>
<%
MessageResponse success = null;
    try{
        success = (MessageResponse) request.getAttribute("success");
    }
    catch (Exception e){
        e.printStackTrace();
    }

    if (success != null){
%>

<style>
  .alert {
    width:600px;
    height: 90px;
    background: rgb(76, 26, 194);
  background: linear-gradient(135deg, rgb(71, 169, 194) 0%,     rgb(19, 21, 131) 100%);
    color: white;
    border-radius: 25px;
    font-size: 1.5rem;
    text-align: center;
    position: absolute;
    top: 15%;
    left: 30%;
   
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
  .button{
    border-radius: 25px;
  width: 20%;
  height: 30px;
  font-size: 1.0rem;
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
  
  <strong ><%= success.getMessage() %></strong> 
  <br></br>
 <form action="${contextPath}/api/auth/viewAllUserPage" method="get" id="myForm">
  
                        <input hidden id="deleteId" name="userId">
                        <button type="submit" class="button" >Ok</button>
                    </form>
</div>







<% } %>