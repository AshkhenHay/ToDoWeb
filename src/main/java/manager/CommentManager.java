package manager;

import db.DBConnectionProvider;
import model.Comment;
import util.DateUtil;

import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.LinkedList;
import java.util.List;

public class CommentManager {

    public  SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private Connection connection;

    UserManager userManager=new UserManager();
    TaskManager taskManager=new TaskManager();

    public CommentManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public boolean create(Comment comment) {
        String sql = "INSERT INTO comment(comment,date,task_id,user_id) Values(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, comment.getComment());
            preparedStatement.setString(2, DateUtil.convertDateToString(comment.getDate()));
            preparedStatement.setLong(3, comment.getTask().getId());
            preparedStatement.setLong(4,comment.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                comment.setId(resultSet.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Comment getById(long id) {

        String sql = "SELECT * FROM comment WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {

                return getCommentFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    public List<Comment> getALL() {
        List<Comment> commentList = new LinkedList<>();
        String sql = "SELECT * FROM comment";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {


                commentList.add(getCommentFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;

    }

    public List<Comment> getALLCommentByTask(long taskId) {
        List<Comment> commentList = new LinkedList<>();
        String sql = "SELECT * FROM comment WHERE task_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, taskId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                commentList.add(getCommentFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;

    }


    public boolean update(long id,Comment comment) {
        String sql = "UPDATE comment SET  comment='" + comment.getComment() + "' WHERE id=" + id;
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
        String sql = "DELETE FROM comment WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;

    }

    private Comment getCommentFromResultSet(ResultSet resultSet) {
        try {

            return Comment.builder()
                    .id(resultSet.getLong(1))
                    .comment(resultSet.getString(2))
                    .date(DateUtil.convertStringToDate(resultSet.getString(3)))
                    .task(taskManager.getById(resultSet.getLong(4)))
                    .user(userManager.getById(resultSet.getLong(5)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
