package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() { // Таблица создаётся
        try (Session session = Util.getSessionFactory().openSession()) { //Работает
            String sql = "CREATE TABLE IF NOT EXISTS newUsers (Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age INT)";
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate(); //Устаревший, но я так и не нашёл чем его заменить
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); //Откатывает транзакцию, если произошла ошибка
            }
        }

    }

    @Override
    public void dropUsersTable() { //Таблица удаляется
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS newUsers";
            session.createSQLQuery(sql).executeUpdate(); //Устаревший, но я так и не нашёл чем его заменить
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) { //работает
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user); //persist полагаю заменяет устаревший save
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void removeUserById(long id) { //работает
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() { //работает
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User").list(); //Тут не имя таблицы
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() { //работает
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "TRUNCATE TABLE newUsers";
            transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
