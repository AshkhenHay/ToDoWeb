<%@ page import="model.Task" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Detail</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="../css/styleWeb.css">
</head>
<body>

<!-- Slideshow container -->
<div class="slideshow-container">

    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="/img/task1.png" style="width:700px;height: 300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="/img/task2.jpg" style="width:700px;height: 300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="/img/task3.png" style="width:700px;height: 300px">

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

<% User user= (User) session.getAttribute("user");
    Task task=(Task) request.getAttribute("taskDetail");
List<Comment> comment =(List<Comment>) request.getAttribute("comments");
%>
<h1 style="text-align: center">Task title:  <%=task.getTitle()%></h1>
<div id="taskDetail">
Task Description:<%=task.getDescription()%><br>
Task Date:<%=task.getDeadLine()%><br>
Task Status:<%=task.getStatus()%><br>
User:<%=task.getUser().getName()%><br>
    <%if (user.getUserType()==UserType.MANAGER){%>
    <a href="/manager">Back</a>
    <%}else{%>
    <a href="/userHome">Back</a>
    <%}%>
    <button id="comment-btn">Comment</button>
</div>

<div id="comment" hidden>
<form action="/addComment" method="post">
    <input type="hidden" name="taskId" value="<%=task.getId()%>">
    <textarea name="comment" placeholder="Add comment"></textarea>
    <input type="submit" value="OK"><br>
</form>

    <%for (Comment comment1:comment) { %>
<p><%=comment1.getComment()%></p>
    <%=comment1.getUser().getName()%><br><%=comment1.getDate()%>
<% if (comment1.getUser().equals(user)){%>
    <a href="/removeComment?id=<%=comment1.getId()%>"><button>X</button></a>
      <%}
        }%>
</div>

<script src="/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="/js/slider.js" type="text/javascript"></script>
<script src="/js/web.js" type="text/javascript"></script>
</body>
</html>
