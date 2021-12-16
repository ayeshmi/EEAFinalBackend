<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DOMSEL Product List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
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
    background-color: #e8e8e8;
    border-radius: 5px;
    overflow: hidden;
    box-shadow: 0 0 4px 0 rgba(15, 4, 4, 0.05);
    transition: all 0.2s ease-out;
    border-color: #202020;
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
<jsp:include page="Navbar.jsp">
    <jsp:param name="page2" value="home2"/>
</jsp:include>
    <div class = "main-wrapper">
        <div class = "container">
            <div class = "main-title">
                <h1>DOMSEL Shop List</h1>
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
                <div class = "item">
                    <div class = "item-img">
                        <img src = "https://remede.com.au/wp-content/uploads/2016/11/iStock-472537538-Women-Health.jpg">
                    </div>
                    <div class = "item-detail">
                        <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/WomanHealth" class = "item-name" >Woman Health</a>
                        
                        <p> Women's Health is your go-to destination for new workouts, legit nutrition advice and weight loss tips, the latest health news, healthy recipes, and more.</p>
                        
                    </div>
                </div>

                <div class = "item">
                  <div class = "item-img">
                      <img src = "http://www.concordiapetcare.com/wp-content/uploads/2020/07/ConcordiaPetCareMay2020_04-e1596177725294.jpg">
                  </div>
                  <div class = "item-detail">
                      <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/AnimalCare" class = "item-name">Animal Care</a>
                      
                      <p> Protect your animal is very important thing. Because your animal is your best friend. In this section you can see what are the medicines available for animal care.</p>
                      
                  </div>
              </div>

              <div class = "item">
                <div class = "item-img">
                    <img src = "https://content.instructables.com/ORIG/FR9/CTJG/I0R0W0Z6/FR9CTJGI0R0W0Z6.jpg?frame=1&width=2100">
                </div>
                <div class = "item-detail">
                    <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/FirstAid" class = "item-name">First Aid</a>
                    
                    <p> First aid is the first and immediate assistance given to any person suffering from either a minor or serious illness or injury, with care provided to preserve life, prevent the condition from worsening, or to promote recovery.</p>
                    
                </div>
            </div>

            <div class = "item">
              <div class = "item-img">
                  <img src = "https://valleyeyecareaz.com/wp-content/uploads/2018/02/eye-care-11.jpg">
              </div>
              <div class = "item-detail">
                  <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/EyeCare" class = "item-name">Eye Care</a>
                  
                  <p> Your eye care provider examines your eyes using a special magnifying lens. This provides a clear view of important tissues at the back of your eye, including the retina, macula, and optic nerve.</p>
                  
              </div>
          </div>

          <div class = "item">
            <div class = "item-img">
                <img src = "https://va.gapitc.org/wp-content/uploads/2019/12/Beyond-the-Basics-Responsibilities-of-a-Child-Care-Provider.jpg">
            </div>
            <div class = "item-detail">
                <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/BabyCare" class = "item-name">Baby Care</a>
                
                <p> The baby care market is composed of different segments and products, such as toys, feeding accessories, wipes, disposable diapers, body care products and soothers, to name a few.</p>
                
            </div>
        </div>

        <div class = "item">
          <div class = "item-img">
              <img src = "https://1stchoiceacupuncture.com/wp-content/uploads/2019/08/Smoking-Cessation-specialty.jpg">
          </div>
          <div class = "item-detail">
              <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/SmokeCessation" class = "item-name">Smoke Cessation</a>
              
              <p> Smoking cessation, usually called quitting smoking or stopping smoking, is the process of discontinuing tobacco smoking. Tobacco smoke contains nicotine, which is addictive and can cause dependence. Nicotine withdrawal often makes the process of quitting difficult.</p>
              
          </div>
      </div>

      <div class = "item">
        <div class = "item-img">
            <img src = "https://rdcnewsadvice.wpengine.com/wp-content/uploads/2018/03/bedroom-ruining-sex-life.jpg">
        </div>
        <div class = "item-detail">
            <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/SexualLife" class = "item-name">Sexual Life</a>
            
            <p> sexual life 'The directions and manifestations of the sexual drive that contribute to a person's life-style, sometimes confined to genital activity and sometimes referring to all of the manifestations of libidinal energy in the subject's personality and relationships'. Cf Sex life.</p>
            
        </div>
    </div>
    <div class = "item">
      <div class = "item-img">
          <img src = "https://media.allure.com/photos/5b7b367d5e0d3a1ac06b3f61/16:9/w_1280,c_limit/fenty-primark-social.jpg">
      </div>
      <div class = "item-detail">
          <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/Cosmetics" class = "item-name">Cosmetics</a>
          
          <p> Cosmetics are constituted mixtures of chemical compounds derived from either natural sources, or synthetically created ones. Cosmetics have various purposes. Those designed for personal care and skin care can be used to cleanse or protect the body or skin.</p>
          
      </div>
  </div>
  <div class = "item">
    <div class = "item-img">
        <img src = "https://cdn.shopify.com/s/files/1/2315/0541/products/38197_image2_1400x.jpg?v=1548937212">
    </div>
    <div class = "item-detail">
        <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/SupportandBandages" class = "item-name">Support and Bandages</a>
        
        <p> A support bandage is a form of bandage that assists the healing of injured muscles and bones, helps slow the bleeding of a wound, or provides support to prevent injuries from occurring. There are many different kinds of supportive bandages, including many types of pressure or compression bandages, typically made of materials such as cloth or elastic .</p>
        
    </div>
</div>
<div class = "item">
  <div class = "item-img">
      <img src = "https://wallpapersmug.com/download/1024x768/eef3e3/beautiful-woman-sweater-bokeh.jpg">
  </div>
  <div class = "item-detail">
      <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/Beauty" class = "item-name">Beauty</a>
      
      <p> Women's Health is your go-to destination for new workouts, legit nutrition advice and weight loss tips, the latest health news, healthy recipes, and more.</p>
      
  </div>
</div>
<div class = "item">
  <div class = "item-img">
      <img src = "https://s3.amazonaws.com/images.ecwid.com/images/17811251/1390924730.jpg">
  </div>
  <div class = "item-detail">
      <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/EarCare" class = "item-name">Ear Care</a>
      
      <p> Human ear, organ of hearing and equilibrium that detects and analyzes sound by transduction (or the conversion of sound waves into electrochemical impulses) and maintains the sense of balance (equilibrium).</p>
      
  </div>
</div>
<div class = "item">
  <div class = "item-img">
      <img src = "https://forthemommas.com/wp-content/uploads/2014/11/dove-hair-care.jpg">
  </div>
  <div class = "item-detail">
      <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/HairCare" class = "item-name">Hair Care</a>
      
      <p> 
        Hair care is an overall term for hygiene and cosmetology involving the hair which grows from the human scalp, and to a lesser extent facial, pubic and other body hair. Hair care routines differ according to an individual's culture and the physical characteristics of one's hair. Hair may be colored, trimmed, shaved, plucked or otherwise removed with treatments such as waxing, sugaring and threading</p>
      
  </div>
</div>
<div class = "item">
  <div class = "item-img">
      <img src = "https://treatheadaches.com/wp-content/uploads/2018/04/headache-and_fever.jpg">
  </div>
  <div class = "item-detail">
      <a href = "${contextPath}/api/auth/viewSelectedCategoryItem/FeverandPainRelief" class = "item-name">Fever and Pain Relief</a>
      
      <p> If you’re looking for relief from the symptoms of a cold, fever, or the flu, you’ll find many over-the-counter (OTC) options at your local pharmacy. The pain and fever-reducing ingredients often found in these medicines</p>
      
  </div>
</div>

              
               
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