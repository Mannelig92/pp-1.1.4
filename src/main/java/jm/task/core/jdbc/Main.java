package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

/*
1. Util - подключаем нашу БД (тут самое лёгкое "почти") +?
2. UserServiceImpl - внутри класса создаём экземпляр UserDaoJDBCImpl (дальше сообразите что делать, идея поможет)))
3. User - тут только переопределяем метод toString (идея сама всё cделает) +
4. UserDaoJDBCImpl - здесь расписываем основной функционал с таблицей (создать, удалить, сохранить пользователя и т.д.)
        - создаём: String переменная = "команда на языке БД"
        - создаём: Statement переменная = наш метод из класса Util
        - используя нашу созданную переменную Statement, запихиваем туда нашу переменную String (та, что с командами БД)

 */
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

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
