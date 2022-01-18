<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
.input{
  width: 500px;
  height: 50px;
  align-content: center;
  border-radius: 55px;
  position: absolute;
  top:15%;
  left: 20%;
  font-size: 1.5rem;
  background: rgb(234, 235, 240);
  background: linear-gradient(135deg, rgb(213, 210, 221) 0%,     rgb(77, 143, 209) 100%);
  padding-left: 1.5rem;
  border: none;
}
.button{
  width: 150px;
  height: 50px;
  position: absolute;
  top:15%;
  right: 36%;
  border-radius: 55px;
  font-size: 1.5rem;
  color: white;
  font-weight: 700;
  background: rgb(234, 235, 240);
  background: linear-gradient(135deg, rgb(122, 97, 180) 0%,     rgb(77, 143, 209) 100%);
  border: 0px;
  cursor: pointer;
  transition: opacity 0.25s ease-out;
}
</style>
<div>
<form action="/api/auth/advanceItemSearchUser" method="get">
  <input type="text" id="typeName" class="input"
    placeholder="  Search any medicine from here..." name="search" var="search"/>
  <button  class="button" >Search</button>
  </form>
</div>