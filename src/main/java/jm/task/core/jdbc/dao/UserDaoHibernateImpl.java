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
        Session sess = sessionFact.openSession();
        try {
            Transaction transact = sess.beginTransaction();
            sess.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            if (sess != null) {
                System.out.println("Table create successful");
                sess.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session sess = sessionFact.openSession();
        try {
            Transaction transact = sess.beginTransaction();
            sess.createSQLQuery("DROP TABLE User").addEntity(User.class).executeUpdate();
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            if (sess != null) {
                System.out.println("Table drop successful");
                sess.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session sess = sessionFact.openSession();
        try {
            Transaction transact = sess.beginTransaction();
            sess.save(new User(name, lastName, age));
            transact.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            if (sess != null) {
                System.out.println("User ?? ???????????? " + name + " ???????????????? ?? ???????? ????????????");
                sess.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session sess = sessionFact.openSession();
        try {
            Transaction transact = sess.beginTransaction();
            User user = (User) sess.get(User.class, id);
            sess.delete(user);
            System.out.println("User " + user.getName() + " delete successful");
            sess.getTransaction().commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            if (sess != null) {
                sess.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> array = new ArrayList<>();
        Session sess = sessionFact.openSession();
        try {
            Transaction transact = sess.beginTransaction();
            array = sess.createCriteria(User.class).list();
            sess.getTransaction().commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            if (sess != null) {
                sess.close();
            }
        }
        return array;
    }

    @Override
    public void cleanUsersTable() {
        Session sess = sessionFact.openSession();
        try {
            Transaction transact = sess.beginTransaction();
            sess.createSQLQuery("DELETE FROM User").executeUpdate();
            sess.getTransaction().commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            if (sess != null) {
                sess.close();
            }
        }
    }
}
