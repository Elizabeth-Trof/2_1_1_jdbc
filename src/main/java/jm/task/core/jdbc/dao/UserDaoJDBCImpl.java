package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id mediumint not null auto_increment," +
                    " name VARCHAR(50), lastname VARCHAR(50), age tinyint, PRIMARY KEY (id))");
            System.out.println("Таблица создана");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Drop table if exists users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " " + lastName + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users where id");
            System.out.println("Пользователь удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT id,name,lastname,age from users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM jbdc_maven.users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
