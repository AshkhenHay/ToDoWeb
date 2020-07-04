<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>

    All Tasks
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Title</td>
            <td>Description</td>
            <td>Deadline</td>
            <td>Status</td>
            <td>User</td>
            <td>Delete</td>
            <td>Info</td>
        </tr>

        <% for (Task task : tasks) {%>
        <tr>
            <td><%=task.getId()%>
            </td>
            <td><%=task.getTitle()%>
            </td>
            <td><%=task.getDescription()%>
            </td>
            <td><%=task.getDeadLine()%>
            </td>
            <td><%=task.getStatus()%>
            </td>
            <td><%=task.getUser().getName()%>
            </td>
            <td><a href="/removeTask?task_id=<%=task.getId()%>">Delete</a>
            <td><a href="/taskDetail?task_id=<%=task.getId()%>">Task Detail </a>
        </tr>
        <%}%>

    </table>
