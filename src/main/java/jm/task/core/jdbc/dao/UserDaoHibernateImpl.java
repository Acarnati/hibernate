package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFact = Util.connection();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE User (" +
                "id bigint(20) NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(100), lastName VARCHAR(100), " +
                "age SMALLINT NOT NULL, PRIMARY KEY (id))";
        try (Session sess = sessionFact.openSession()) {
            Transaction transact = sess.beginTransaction();
            sess.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            System.out.println("Table create successful");
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session sess = sessionFact.openSession()) {
            Transaction transact = sess.beginTransaction();
            sess.createSQLQuery("DROP TABLE User").addEntity(User.class).executeUpdate();
            System.out.println("Table drop successful");
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session sess = sessionFact.openSession()) {
            Transaction transact = sess.beginTransaction();
            sess.save(new User(name, lastName, age));
            System.out.println("User с именем " + name + " добавлен в базу данных");
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session sess = sessionFact.openSession()) {
            Transaction transact = sess.beginTransaction();
            User user = (User) sess.get(User.class, id);
            sess.delete(user);
            System.out.println("User " + user.getName() + " delete successful");
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> array = new ArrayList<>();
        try (Session sess = sessionFact.openSession()) {
            Transaction transact = sess.beginTransaction();
            array = sess.createQuery("from User", User.class).getResultList();
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return array;
    }

    @Override
    public void cleanUsersTable() {
        try (Session sess = sessionFact.openSession()) {
            Transaction transact = sess.beginTransaction();
            sess.createSQLQuery("DELETE FROM User").executeUpdate();
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
