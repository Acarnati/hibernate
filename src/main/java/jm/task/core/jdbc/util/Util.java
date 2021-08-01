package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    public static SessionFactory session = null;
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/base?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static SessionFactory connection() {
        if (session == null) {
            try {
                Configuration conf = new Configuration()
                        .setProperty("hibernate.connection.driver_class", JDBC_DRIVER)
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USER)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .addAnnotatedClass(User.class);
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(conf.getProperties()).build();
                session = conf.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return session;
    }
}
