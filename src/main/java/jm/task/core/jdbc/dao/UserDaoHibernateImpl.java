package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age INT)";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();

        session.close();

    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS Users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();

            System.out.println("Таблица 'users' успешно удалена, если существовала.");

            session.close();
        } catch (Exception e) {



            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);

            transaction.commit();
            System.out.println("Пользователь " + name + " " + lastName + " успешно добавлен.");

            session.close();
        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }

            transaction.commit();
            System.out.println("Пользователь с ID " + id + " удален успешно.");

            session.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            users = session.createQuery("FROM User").list();

            transaction.commit();

            session.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return users;


    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE FROM Users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
            System.out.println("Таблица 'users' очищена от всех записей.");

            session.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
