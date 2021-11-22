<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Furniture Product List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
</head>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');

*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
body{
    font-family: 'Poppins', sans-serif;
}
img{
    width: 100%;
    display: block;
}
.main-wrapper{
    background-color: #fff;
    min-height: 100vh;
    overflow-x: 0;
}
.container{
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 16px;
}
.main-title{
    text-align: center;
}
.main-title h1{
    padding: 16px 0;
    font-weight: 500;
    text-transform: uppercase;
    letter-spacing: 2px;
}
.display-style-btns{
    margin: 10px 0;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    background-color: #fff;
    padding: 16px 0;
    border-radius: 5px;
}
.display-style-btns button{
    border: none;
    font-size: 22px;
    display: inline-block;
    vertical-align: top;
    margin: 0 8px;
    background-color: transparent;
    cursor: pointer;
    transition: all 0.3s ease-out;
}
.display-style-btns button:hover{
    color: #f79410;
}
.active-btn{
    color: #f79410;
}


.item-list{
    margin: 36px 0;
    display: grid;
    grid-template-columns: repeat(1, 1fr);
    row-gap: 32px;
}
.item{
    background-color: #fff;
    border-radius: 5px;
    overflow: hidden;
    box-shadow: 0 0 4px 0 rgba(15, 4, 4, 0.05);
    transition: all 0.2s ease-out;
}
.item:hover{
    box-shadow: 0 0 10px 1px rgba(0, 4, 4, 0.15);
}
.item-img{
    position: relative;
    overflow: hidden;
}
.item-img img{
    width: 70%;
    margin: 16px auto;
}
.icon-list{
    position: absolute;
    bottom: -100px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease-out;
}
.icon-list button{
    border: none;
    background-color: #202020;
    color: #fff;
    margin: 0 6px;
    width: 35px;
    height: 35px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 15px;
    cursor: pointer;
    transition: all 0.3s ease-out;
}
.icon-list button:hover{
    background-color: #f4f4f4;
    color: #202020;
}
.item-img:hover .icon-list{
    bottom: 26px;
}
.item-detail{
    padding: 16px;
    color: #202020;
    text-align: center;
}
.item-name{
    font-weight: 500;
    font-size: 18px;
    color: #202020;
    display: block;
}
.item-price{
    margin: 10px 0;
    font-weight: 300;
    display: flex;
    align-items: center;
    justify-content: center;
}
.old-price{
    text-decoration: line-through;
    opacity: 0.6;
}
.new-price{
    color: #f79410;
    font-size: 18px;
    font-weight: 600;
    margin-right: 10px;
}
.item-detail p{
    font-weight: 300;
    opacity: 0.9;
    font-size: 15px;
    line-height: 1.7;
    display: none;
}
.add-btn{
    margin: 16px 0;
    text-transform: uppercase;
    border: none;
    background-color: #202020;
    color: #fff;
    font-family: inherit;
    padding: 10px 28px;
    border: 1px solid #202020;
    cursor: pointer;
    transition: all 0.3s ease-out;
    display: none;
}
.add-btn:hover{
    background-color: #fff;
    color: #202020;
}
img{
width:200px;
height:200px;
}

/* stylings for details active */
.details-active.item-list{
    grid-template-columns: 100%;
}
.details-active .item{
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    align-items: center;
}
.details-active .item-detail{
    text-align: left;
}
.details-active .item-price{
    justify-content: flex-start;
}
.details-active .item-detail p{
    display: block;
}
.details-active .item-detail .add-btn{
    display: block;
}



@media screen and (min-width: 678px){
    .item-list{
        grid-template-columns: repeat(2, 1fr);
        gap: 32px;
    }
}

@media screen and (min-width: 768px){
    .item-list{
        grid-template-columns: repeat(3, 1fr);
    }
}

@media screen and (max-width: 576px){
    .details-active .item{
        grid-template-columns: 100%;
    }
}
</style>
<body>
    <div class = "main-wrapper">
        <div class = "container">
            <div class = "main-title">
                <h1>Item List</h1>
            </div>
            <div class = "display-style-btns">
                <button type = "button" id = "grid-active-btn">
                    <i class = "fas fa-th"></i>
                </button>
                <button type = "button" id = "details-active-btn">
                    <i class = "fas fa-list-ul"></i>
                </button>
            </div>

            <div class = "item-list">
             <c:forEach var="item" items="${items}">
                <div class = "item">
                    <div class = "item-img">
                        <img src = ${item.image}>
                        <div class = "icon-list">
                            <button type = "button">
                                <i class = "fas fa-sync-alt"></i>
                            </button>
                            <button type = "button">
                                <i class = "fas fa-shopping-cart"></i>
                            </button>
                            <button type = "button">
                                <i class = "far fa-heart"></i>
                            </button>
                        </div>
                    </div>
                    <div class = "item-detail">
                        <a href = "${contextPath}/api/auth/viewItemByItem/${item.id}" class = "item-name">${item.name}</a>
                        <div class = "item-price">
                            <span class = "new-price">${item.price}</span>
                            <span class = "old-price">$275.60</span>
                        </div>
                        <p>${item.description}</p>
                        <button type = "button" class = "add-btn">add to cart</button>
                    </div>
                </div>
</c:forEach>
                

               
            </div>
        </div>
    </div>



    <script >const itemList = document.querySelector('.item-list');
      const gridViewBtn = document.getElementById('grid-active-btn');
      const detailsViewBtn = document.getElementById('details-active-btn');
      
      gridViewBtn.classList.add('active-btn');
      
      gridViewBtn.addEventListener('click', () => {
          gridViewBtn.classList.add('active-btn');
          detailsViewBtn.classList.remove('active-btn');
          itemList.classList.remove('details-active');
      });
      
      detailsViewBtn.addEventListener('click', () => {
          detailsViewBtn.classList.add('active-btn');
          gridViewBtn.classList.remove("active-btn");
          itemList.classList.add("details-active");
      });</script>
</body>
</html>