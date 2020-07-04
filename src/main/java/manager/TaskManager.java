package manager;

import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;
import util.DateUtil;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class TaskManager {

    public SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd ");
    public TaskManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    private Connection connection;

UserManager userManager=new UserManager();



    public boolean create(Task task) {
        String sql = "INSERT INTO task(title,description,deadline,status,user_id) Values(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            if(task.getDeadLine()!=null) {
                preparedStatement.setString(3, DateUtil.convertDeadlineToString(task.getDeadLine()));
            }else{
                preparedStatement.setString(3, null);
            }
            preparedStatement.setString(4, task.getStatus().name());
            preparedStatement.setLong(5,task.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                task.setId(resultSet.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Task getById(long id) {

        String sql = "SELECT * FROM task WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {

                return getTaskFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    public List<Task> getALL() {
        List<Task> taskList = new LinkedList<>();
        String sql = "SELECT * FROM task";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {


                taskList.add(getTaskFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;

    }

    public List<Task> getALLTaskByUser(long userId) {
        List<Task> taskList = new LinkedList<>();
        String sql = "SELECT * FROM task WHERE user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                taskList.add(getTaskFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;

    }


    public boolean update(long id, TaskStatus status) {
        String sql = "UPDATE task SET   status='" + status.name().toUpperCase() + "' WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;


    }

    public boolean delete(long id) {
        String sql = "DELETE FROM task WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {

            return Task.builder()
                    .id(resultSet.getLong(1))
                    .title(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .deadLine(resultSet.getString(4)==null ? null :DateUtil.convertStringToDeadline(resultSet.getString(4)))
                    .status(TaskStatus.valueOf(resultSet.getString(5)))
                    .user(userManager.getById(resultSet.getLong(6)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}

