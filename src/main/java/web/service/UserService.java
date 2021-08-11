package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUserService();
    void createUserService(User user);
    void deleteUserService(int id);
    User getUserByIdService(int id);
    void updateUserService(User user);
}
