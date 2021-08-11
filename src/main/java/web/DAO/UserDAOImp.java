package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDAOImp implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public List<User> getAllUser() {
        return getEntityManager().createQuery("from User").getResultList();
    }

    @Override
    public void createUser(User user) {
        getEntityManager().persist(user);
        getEntityManager().flush();
    }

    @Override
    public void deleteUser(int id) {
        getEntityManager().createQuery("delete from User where id=: id").setParameter("id", id).executeUpdate();
        getEntityManager().flush();
    }

    @Override
    public User getUserById(int id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public void updateUser(User user) {

        getEntityManager().merge(user);
        getEntityManager().flush();
    }
}
