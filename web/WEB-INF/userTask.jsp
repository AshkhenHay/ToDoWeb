<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/styleWeb.css">
</head>
<body>

<!-- Slideshow container -->
<div class="slideshow-container">

    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="/img/userimg1.jpg" style="width:700px;height:300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="/img/userimg2.jpg" style="width:700px;height: 300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="/img/userimg3.jpeg" style="width:700px;height: 300px">

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

<% User user = (User) session.getAttribute("user");
    List<Task> tasks = (List<Task>) request.getAttribute("todos");
%>

<h1 style="text-align: center"><%=user.getName()%>  <%=user.getSurname()%></h1>
<button style="float: right; font-size: 24px"><a href="/logout">Logout</a></button>
<div id="img">
<%if (user.getPictureUrl()!=null) {%>
<img src="/image?path=<%=user.getPictureUrl()%>"width="100"/><%}%><br>
<form action="/addImage" method="post" enctype="multipart/form-data">
    <input type="file"name="image" enctype="multipart/form-data"><br>
    <input type="submit" value="ok">
</form>
</div>

<div id="userTask">
All Tasks
<table border="1">
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Deadline</td>
        <td>Status</td>
        <td>UserName</td>
        <td>UpdateStatus</td>
        <td>Info</td>

    </tr>
    <% for (Task task : tasks) {%>
    <tr>
        <td><%=task.getId()%>
        </td>
        <td><%=task.getTitle()%>
        </td>
        <td><%=task.getDeadLine()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getUser().getName()%>
        </td>
<td>
    <form action="/updateTaskStatus" method="post">
        <input type="hidden" name="taskId" value="<%=task.getId()%>">
        <select name="status">
            <option value="TODO">TODO</option>
            <option value="IN_PROGRESS">IN_PROGRESS</option>
            <option value="FINISHED">FINISHED</option>
        </select> <input type="submit"value="ok"><br>
    </form>
        </td>
        <td> <a href="/taskDetail?task_id=<%=task.getId()%>">Task Detail </a>
    </tr>
    <%}%>

</table>
</div>


<script src="/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="/js/slider.js" type="text/javascript"></script>
</body>
</html>
