package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        User user1 = new User("Ivan", "Ivanov", (byte)22);
        User user2 = new User("Igor", "Sidorov", (byte)30);
        User user3 = new User("Irina", "Fedorovna", (byte)35);
        User user4 = new User("Elena", "Semenovna", (byte)23);
        user.createUsersTable();
        user.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        user.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        user.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        user.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> allUsers = user.getAllUsers();
        allUsers.forEach(System.out::println);
        user.removeUserById(3);
        List<User> allUsers1 = user.getAllUsers();
        allUsers1.forEach(System.out::println);
        user.cleanUsersTable();
        user.dropUsersTable();
        if (Util.session != null)
            Util.session.close();
    }
}
