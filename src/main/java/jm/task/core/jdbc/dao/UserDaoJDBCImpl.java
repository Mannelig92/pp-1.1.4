package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { //создание таблицы, если её нет
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Users (Id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age INT)";
            statement.executeUpdate(sql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    executeUpdate: выполняет такие команды, как INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE.
    В качестве результата возвращает количество строк, затронутых операцией (например, количество добавленных,
    измененных или удаленных строк), или 0, если ни одна строка не затронута операцией или если команда не изменяет
    содержимое таблицы (например, команда создания новой таблицы)
     */
    public void dropUsersTable() { //удаление таблицы
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(sql);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) { //добавление юзера
        String sql = "INSERT Users(name, lastName, age) VALUES(?, ?, ?)";
        try (PreparedStatement pps = Util.getConnection().prepareStatement(sql)) {
            pps.setString(1, name);
            pps.setString(2, lastName);
            pps.setInt(3, age);
            pps.executeUpdate();
            System.out.println("Пользователь " + name + " " + lastName + " успешно добавлен в таблицу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) { //удаление юзера по айди
        try (PreparedStatement pps = Util.getConnection().prepareStatement("DELETE FROM Users WHERE ID = ?")) {
            pps.setLong(1, id);
            pps.executeUpdate();
            System.out.println("Пользователь с id:" + id + " удалён из таблицы");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() { //добавление всех юзеров в список
        List<User> listOfUsers = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                listOfUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return listOfUsers;
    }

    public void cleanUsersTable() { //очистка таблицы
        String sql = "TRUNCATE TABLE Users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Все пользователи из таблицы удалены");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
