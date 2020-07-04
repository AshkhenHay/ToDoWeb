package manager;

import db.DBConnectionProvider;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserManager {
    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void register(User user) {
        String sql = "INSERT INTO user(name,surname,email,password,user_type,picture_url) Values(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUserType().name());
            preparedStatement.setString(6,user.getPictureUrl());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {

                user.setId(resultSet.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public List<User> getallUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user  ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email=? AND password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public User getById(long id) {
        String sql = "SELECT * FROM user WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {

                return getUserFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public boolean removeUserById(int userId) {
        String sql = "DELETE FROM user WHERE id=" + userId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void updateImage(String imgPath,long id) {
        String sql = "UPDATE user SET   picture_url='" + imgPath + "' WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private User getUserFromResultSet(ResultSet resultSet) {

        try {
            return User.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .userType(UserType.valueOf(resultSet.getString(6)))
                    .pictureUrl(resultSet.getString(7))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


}
