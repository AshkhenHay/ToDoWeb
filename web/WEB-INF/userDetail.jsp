<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserDetail</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="../css/styleWeb.css">
</head>
<body>
<!-- Slideshow container -->
<div class="slideshow-container">

    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="/img/userdimg1.jpg" style="width:700px;height: 300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="/img/userdimg2.jpg" style="width:700px;height: 300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="/img/userdimg3.jpg" style="width:700px;height: 300px">

    </div>

    <!-- Next and previous buttons -->
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<!-- The dots/circles -->
<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
</div>

<% User user= (User) request.getAttribute("userDetail");%>

<h1 style="text-align: center"><%=user.getName()%>  <%=user.getSurname()%></h1>

<div id="userDetail">
    <img src="/image?path=<%=user.getPictureUrl()%>"width="100" /><br>
Surname:<%=user.getSurname()%><br>
Email:<%=user.getEmail()%><br>
User Type:<%=user.getUserType()%><br>
<a href="/manager">Back</a>
</div>
<script src="/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="/js/slider.js" type="text/javascript"></script>
</body>
</html>
