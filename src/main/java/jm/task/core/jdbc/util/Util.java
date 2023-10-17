package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
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




    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();


            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", URL_DB);
            configuration.setProperty("hibernate.connection.username", NAME_USER);
            configuration.setProperty("hibernate.connection.password", PASSWORD_USER);


            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");


            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Создание SessionFactory не удалось" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("Ошибка подключения");
        }
        return sessionFactory;
    }




}
