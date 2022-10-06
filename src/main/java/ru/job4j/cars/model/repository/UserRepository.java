package ru.job4j.cars.model.repository;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private static final Logger LOG = LogManager.getLogger(UserRepository.class.getName());
    @Getter
    private final SessionFactory sf;

    public User createUser(User user) {
        User rsl = new User(user.getLogin(), user.getPassword());
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl.setId((int) session.save(user));
            session.getTransaction().commit();
            session.close();
        }
        return rsl;
    }

    public void update(User user) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery(
                    "UPDATE User SET login = :fLogin, password = :fPassword WHERE id = :fId")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    public void delete(int userId) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.delete(new User(userId));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    public List<User> findAllOrderById() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<User> rsl = session.createQuery(
                    "FROM User ORDER BY id")
                            .list();
            session.getTransaction().commit();
            session.close();
            return rsl;
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new ArrayList<>();
    }

    public Optional<User> findById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            User rsl = (User) session.createQuery("FROM User WHERE id = :fId")
                    .setParameter("fId", id)
                    .getSingleResult();
            session.getTransaction().commit();
            session.close();
            return Optional.of(rsl);
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return Optional.empty();
    }

    public List<User> findByLikeLogin(String login) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<User> rsl = session.createQuery(
                    "FROM User WHERE login = :fLogin")
                    .setParameter("fLogin", login)
                    .list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new ArrayList<>();
    }

    public Optional<User> findByLogin(String login) {
        return findByLikeLogin(login).stream().findFirst();
    }



}
