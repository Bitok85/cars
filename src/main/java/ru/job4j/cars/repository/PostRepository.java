package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository {

    private static final Logger LOG = Logger.getLogger(PostRepository.class.getName());

    private final CrudRepository crudRepository;

    public List<Post> findByLastDay() {
        return crudRepository.query(PostHQLs.LAST_DAY_POSTS.getHql(), Post.class);
    }

    public List<Post> findWithPhoto() {
        return crudRepository.query(PostHQLs.POSTS_WITH_PHOTO.getHql(), Post.class);
    }

    public List<Post> findByBrand(String brand) {
        return crudRepository.query(PostHQLs.POSTS_BY_BRAND.getHql(), Post.class, Map.of("fModel", brand));
    }
}
