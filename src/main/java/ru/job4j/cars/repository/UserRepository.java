package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@ThreadSafe
public class UserRepository {

    private static final Logger LOG = Logger.getLogger(UserRepository.class.getName());
    private CrudRepository crudRepository;

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "FROM User WHERE :fLogin = login AND :fPassword = password",
                User.class,
                Map.of("fLogin", login, "fPassword", password));
    }

    public Optional<User> addUser(User user) {
        try {
            crudRepository.run(session -> session.save(user));
            return Optional.ofNullable(user);
        } catch (Exception e) {
            LOG.error("Add user error", e);
        }
        return Optional.empty();
    }

}
