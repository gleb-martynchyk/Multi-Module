package org.jazzteam.martynchyk;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class UsersJDBC {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static boolean isPasswordCorrect(String login, String password) {
        String query = "select case " +
                "when \"" + password.hashCode() + "\" = (" +
                "select password " +
                "from users " +
                "where login = \"" + login + "\"" +
                ") " +
                "then 1 " +
                "else 0 end as BIT";
        boolean isCorrect = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //connection = DriverManager.getConnection(UsersJDBC.url, UsersJDBC.user, UsersJDBC.password);
            connection = UsersJDBC.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                isCorrect = resultSet.getBoolean(1);
            }

        } catch (SQLException | ClassNotFoundException | IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException se) {
            }
            try {
                statement.close();
            } catch (SQLException se) {
            }
            try {
                resultSet.close();
            } catch (SQLException se) {
            }
        }
        return isCorrect;
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("database.properties")) {
            properties.load(inputStream);
        }

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
