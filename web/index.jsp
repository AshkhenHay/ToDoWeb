<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/styleWeb.css">

</head>
<body>

<!-- Slideshow container -->
<div class="slideshow-container">

    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="/img/img.png" style="width:700px;height: 300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="/img/welc.jpg" style="width:700px;height:300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="/img/welcome.jpg" style="width:700px;height: 300px">

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

<%
    String msg = "";
    if (session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }

%>
<p style="color: red">
    <%=msg%>
</p>
<div id="login">
Login
<form action="/login" method="post">
    <input type="text" name="username" placeholder="Username" required/><br>
    <input type="password" name="password" placeholder="Password" required/><br>
    <input type="submit" value="Login" style="width: 150px">

</form>
    <button id="register-btn" style="width: 150px">Register</button>
</div>
<div id="register" hidden>
Register
<form action="/register" method="post" >
    <input type="text" name="name" placeholder="Name" required/><br>
    <input type="text" name="surname" placeholder="Surname" required/><br>
    <input type="text" name="username" placeholder="Username" required/><br>
    <input type="password" name="password" placeholder="Password"required/><br>
    <input type="submit" value="Register" style="width: 150px">
</form>
</div>
<script src="/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="/js/slider.js" type="text/javascript"></script>
<script src="/js/web.js" type="text/javascript"></script>
</body>

</html>
