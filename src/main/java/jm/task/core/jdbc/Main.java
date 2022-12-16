package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        UserServiceImpl userService = new UserServiceImpl();

        userDao.createUsersTable();

        userDao.saveUser("John", "Williams", (byte) 18);
        userDao.saveUser("Steve", "Stolen", (byte) 51);
        userDao.saveUser("Karl", "Grant", (byte) 31);
        userDao.saveUser("Bruce", "Willis", (byte) 73);

//        userDao.removeUserById(1);
        userDao.getAllUsers();
        userService.getAllUsers();

//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
