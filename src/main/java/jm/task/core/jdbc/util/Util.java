package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static final String Name_User = "root";
    private static final String Password_User = "admin12345";
    private static final String Url_Db = "jdbc:mysql://localhost:3306/mysql";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Url_Db, Name_User, Password_User);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }




}
