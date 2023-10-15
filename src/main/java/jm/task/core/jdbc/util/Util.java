package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static final String NAME_USER = "root";
    private static final String PASSWORD_USER = "admin12345";
    private static final String URL_DB = "jdbc:mysql://localhost:3306/mysql";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL_DB, NAME_USER, PASSWORD_USER);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }




}
