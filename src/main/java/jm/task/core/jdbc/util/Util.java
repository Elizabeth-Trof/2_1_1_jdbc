package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String dbURL = "jdbc:mysql://localhost:3306/jbdc_maven";
    private static final String dbUserName = "root";
    private static final String dbPassword = "qazwsxedcLiza300600";


    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
            if (!(connection.isClosed())) {
                System.out.println("Cоединение с БД установлено");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка при соединении с базой данных: " + e.getMessage());
        }
        return connection;
    }
}
