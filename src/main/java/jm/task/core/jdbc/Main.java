package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User user1 = new User("Ivan", "Ivanov", (byte)22);
        User user2 = new User("Igor", "Sidorov", (byte)30);
        User user3 = new User("Irina", "Fedorovna", (byte)35);
        User user4 = new User("Elena", "Semenovna", (byte)23);
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        userService.removeUserById(3);
        allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
