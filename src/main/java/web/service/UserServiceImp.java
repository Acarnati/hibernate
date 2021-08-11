package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDAO;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUserService() {
        return userDAO.getAllUser();
    }

    @Override
    public void createUserService(User user) {
        userDAO.createUser(user);
    }

    @Override
    public void deleteUserService(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public User getUserByIdService(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUserService(User user) {
        userDAO.updateUser(user);
    }
}
