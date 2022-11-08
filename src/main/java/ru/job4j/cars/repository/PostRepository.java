package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository {

    private static final Logger LOG = Logger.getLogger(PostRepository.class.getName());

    private static final String FIND_BY_ID = "FROM Post WHERE :pId = id";
    private static final String FIND_ALL_NOT_SOLD = "FROM Post WHERE :pSold = sold";
    private static final String FIND_ALL_BY_USER = "FROM Post WHERE :pUserId = auto_user_id";
    private static final String LAST_DAY_POSTS = "SELECT p FROM Post p WHERE p.created >= current_date";
    private static final String POSTS_WITH_PHOTO = "FROM Post WHERE photo IS NOT NULL";
    private static final String POSTS_BY_BRAND
            = "SELECT DISTINCT post FROM Post post JOIN FETCH post.car car WHERE car.brand = :cBrand";
    private static final String DELETE = "DELETE FROM Post WHERE :pId = id";

    private final CrudRepository crudRepository;

    public Optional<Post> addPost(Post post) {
        try {
            crudRepository.run(session -> session.save(post));
            return Optional.ofNullable(post);
        } catch (Exception e) {
            LOG.error("Add post error");
        }
        return Optional.empty();
    }
    public boolean updatePost(Post post) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.update(post));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Post update error", e);
        }
        return rsl;
    }

    public boolean deletePost(int id) {
        boolean rsl = false;
        try {
            crudRepository.query(DELETE, Post.class, Map.of("pId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Delete post error", e);
        }
        return rsl;
    }

    public Optional<Post> findById(int id) {
        try {
            return crudRepository.optional(FIND_BY_ID, Post.class, Map.of("pId", id));
        } catch (Exception e) {
            LOG.error("Find by id error");
        }
        return Optional.empty();
    }

    public List<Post> findAllNotSold() {
        return crudRepository.query(FIND_ALL_NOT_SOLD, Post.class, Map.of("pSold", false));
    }

    public List<Post> findAllByUser(User user) {
        return crudRepository.query(FIND_ALL_BY_USER, Post.class, Map.of("pUserId", user.getId()));
    }

    public List<Post> findByLastDay() {
        return crudRepository.query(LAST_DAY_POSTS, Post.class);
    }

    public List<Post> findWithPhoto() {
        return crudRepository.query(POSTS_WITH_PHOTO, Post.class);
    }

    public List<Post> findByBrand(String brand) {
        return crudRepository.query(POSTS_BY_BRAND, Post.class, Map.of("cBrand", brand));
    }
}
