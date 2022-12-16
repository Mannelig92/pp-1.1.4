package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {

    public static void main(String[] args) {

        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("John", "Williams", (byte) 18);
        userDaoHibernate.saveUser("Steve", "Stolen", (byte) 51);
        userDaoHibernate.saveUser("Karl", "Grant", (byte) 31);
        userDaoHibernate.saveUser("Bruce", "Willis", (byte) 73);


    }
}
