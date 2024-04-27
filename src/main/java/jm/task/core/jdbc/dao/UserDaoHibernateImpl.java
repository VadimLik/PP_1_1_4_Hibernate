package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS myfirstconnect.users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45),  age INT(3))";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS myfirstconnect.users";

    private static final SessionFactory sessionFactory = Util.getSessionFactory();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.createSQLQuery(CREATE_TABLE).executeUpdate();

            session.getTransaction().commit();

        }catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.createSQLQuery(DROP_TABLE).executeUpdate();

            session.getTransaction().commit();

        }catch (HibernateException  e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = new User(name,lastName,age);

            session.save(user);

            session.getTransaction().commit();

            System.out.printf("User с именем - %s добавлен в базу данных \n", user.getName());

        }catch (HibernateException  e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            session.delete(session.get(User.class, id));

            session.getTransaction().commit();

        }catch (HibernateException  e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            users = session.createQuery("from User", User.class).list();

            session.getTransaction().commit();

        }catch (HibernateException  e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.createQuery("delete FROM User").executeUpdate();

            session.getTransaction().commit();

        }catch (HibernateException  e) {
            e.printStackTrace();
        }

    }
}
