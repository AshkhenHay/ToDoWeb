<%@ page import="model.User" %>
<%@ page import="java.util.List" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/styleWeb.css">
</head>
<body>

<!-- Slideshow container -->
<div class="slideshow-container">

    <!-- Full-width images with number and caption text -->
    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="/img/img6.png" style="width:700px;height:300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="/img/img4.jpg" style="width:700px;height: 300px">

    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="/img/img5.jpg" style="width:700px;height: 300px">

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
    User user = (User) session.getAttribute("user");
    List<User> users = (List<User>) request.getAttribute("users");

%>
<h1 style="text-align: center"><%=user.getName()%>  <%=user.getSurname()%></h1><br>

<%if (user.getPictureUrl() != null) {%>
<div id="img">
    <img src="/image?path=<%=user.getPictureUrl()%>" width="100" /><%}%><br>
    <form action="/addImage" method="post" enctype="multipart/form-data">
        <input type="file" name="image" placeholder="Add Image"><br>
        <input type="submit" value="ok">
    </form>

</div>
<div id="btn">

    <span>  <button><a href="/logout">Logout</a></button></span>
    <span> <button  id="addUser-btn">Add User</button></span>
    <span><button  id="addTask-btn">Add Task</button></span>

</div>
<div id="info"></div>

<div id="add">
    <div id="addUser" hidden>
        Add User:
        <form action="/register" method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Name"/><br>
            <input type="text" name="surname" placeholder="Surname"/><br>
            <input type="text" name="username" placeholder="Username"/><br>
            <input type="password" name="password" placeholder="Password"/><br>
            <input type="submit" value="Register">
        </form>
    </div>
    <div id="addTask" hidden>
        Add Task
        <form action="/addTask" method="post" id="addTaskForm">
            <input id="title" name="title" type="text" placeholder="Title"/><br>
            <input id="description" name="description" type="text" placeholder="Description"/><br>
            <input id="deadline" name="deadline" type="date"/><br>
            <select id="status" name="status">
                <option value="TODO">TODO</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="FINISHED">FINISHED</option>
            </select><br>
            <select id="userId" name="user_id">
                <%
                    for (User user1 : users) {%>
                <option value="<%=user1.getId()%>"><%=user1.getName()%><br>
                </option>
                <% }%>

            </select>
            <input type="submit" value="Add"><br>
        </form>
    </div>

</div>
<div id="all">
    <div id="allUsers">
        All Users:
        <table border="1">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Surname</td>
                <td>Email</td>
                <td>Delete</td>
                <td>Info</td>
            </tr>
            <% for (User user1 : users) { %>

            <tr>
                <td><%=user1.getId()%>
                </td>
                <td><%=user1.getName()%>
                </td>
                <td><%=user1.getSurname()%>
                </td>
                <td><%=user1.getEmail()%>
                </td>
                <td><a href="/removeUser?user_id=<%=user1.getId()%>">Delete</a>
                </td>
                <td><a href="/userDetail?user_id=<%=user1.getId()%>">User Detail </a>
                </td>
            </tr>
            <%}%>
        </table>
    </div>
    <div id="allTasks">
loading...
    </div>
</div>
<script src="/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="/js/slider.js" type="text/javascript"></script>
<script src="/js/web.js" type="text/javascript"></script>

</body>
</html>
