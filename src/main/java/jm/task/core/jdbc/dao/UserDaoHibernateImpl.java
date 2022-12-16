package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

/*
Вопросы:
1)Не сохраняет объект в БД, не понимаю почему
2)Не получается вызвать метод createSQLQuery, полагаю им нужно заменить createQuery
3)Вместо save использовать persist?
4)Что использовать из более нового для сохранение в БД?
 */
public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) { //Работает, но исполняется каждый раз, хотя стоит IF NOT EXISTS
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS newUsers (Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";
            Query query = session.createQuery(sql); //Устаревший, но я так и не нашёл чем его заменить
            query.executeUpdate();
            System.out.println("Таблица создана");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException();
        }

    }

    @Override
    public void dropUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) { //Работает
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS newUsers";

            Query query = session.createNativeQuery(sql); //Устаревший, но я так и не нашёл чем его заменить
            query.executeUpdate();

            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) { //Не работает
        try (Session session = Util.getSessionFactory().openSession()) {

            User user = new User(name, lastName, age);
            session.persist(user); //persist заменяет устаревший save

            transaction = session.beginTransaction();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException();
        }
    }

    @Override
    public void removeUserById(long id) { //Не проверял
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getAllUsers() { //Вроде должно быть правильно, но ещё не проверить
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("Пользователи", User.class).list();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void cleanUsersTable() { //доделать
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException();
        }
    }
}
