package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connect = (Connection) Util.connection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE User (" +
                "id bigint(20) NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(100), lastName VARCHAR(100), " +
                "age SMALLINT NOT NULL, PRIMARY KEY (id))";
        try (PreparedStatement prep = connect.prepareStatement(sql)) {
            prep.executeUpdate();
            connect.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement stat = connect.prepareStatement("DROP TABLE User")) {
            stat.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement prep = connect.prepareStatement(sql)) {
            prep.setString(1, name);
            prep.setString(2, lastName);
            prep.setByte(3, age);
            System.out.println("User с именем " + name + " добавлен в базу данных");
            prep.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement date = connect.prepareStatement("DELETE FROM User WHERE id")) {
            date.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> array = new ArrayList<>();
        try (PreparedStatement date = connect.prepareStatement("SELECT * FROM User")) {
            ResultSet result = date.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));
                user.setId(result.getLong("Id"));
                array.add(user);
                connect.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return array;
    }

    public void cleanUsersTable() {
        try (PreparedStatement date = connect.prepareStatement("DELETE FROM User")) {
            date.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
