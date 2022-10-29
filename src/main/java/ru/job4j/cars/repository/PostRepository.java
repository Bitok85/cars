package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    public List<Post> findByLastDay() {
        return crudRepository.query("FROM Post p WHERE p.created >= NOW() - INTERVAL '24 HOURS'", Post.class);
    }

    public List<Post> findWithPhoto() {
        return crudRepository.query("FROM Post WHERE photo IS NOT NULL", Post.class);
    }

    public List<Post> findByBrand(String brand) {
        return crudRepository.query(
                "FROM Post as post LEFT JOIN Car as car WITH car.model = :fModel",
                Post.class, Map.of("fModel", brand));
    }
}
