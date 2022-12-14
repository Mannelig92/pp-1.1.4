package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";// или jdbc:mysql://localhost:3306/mysql
    private static final String USERNAME = "Mannelig";
    private static final String PASSWORD = "199227";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //соединение с БД
            if (!connection.isClosed()) { //Проверка на соединение
                System.out.println("Соединение с БД установлено");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Не удалось установить соединение с БД");
        }
        return connection;
    }

}
