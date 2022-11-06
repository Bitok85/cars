package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final PostRepository postRepository = new PostRepository(crudRepository);
    private final UserRepository userRepository = new UserRepository(crudRepository);


    @AfterEach
    public void wipeTable() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM auto_post").executeUpdate();
            session.createSQLQuery("DELETE FROM auto_user").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddUniqueUserThenReturnsNotEmpty() {
        User user = new User("test", "123");
        assertThat(userRepository.addUser(user)).hasValue(user);
    }

    @Test
    public void whenAddSameAlreadyExistingLogin() {
        User user = new User("test", "123");
        userRepository.addUser(user);
        assertThat(userRepository.addUser(new User("test", "456"))).isEmpty();
    }

    @Test
    public void whenAddAndFindByLoginAndPassword() {
        User user = new User("test", "123");
        userRepository.addUser(user);
        assertThat(userRepository.findByLoginAndPassword("test", "123")).hasValue(user);
    }

    @Test
    public void whenAddPostThenFindSamePost() {
        Post post = new Post("test", false);
        postRepository.addPost(post);
        assertThat(postRepository.findById(post.getId())).hasValue(post);
    }

    @Test
    public void findAllPostsOnSale() {
        List<Post> posts = List.of(
                new Post("test1", false),
                new Post("test2", true),
                new Post("test3", false)
        );
        posts.forEach(postRepository::addPost);
        assertThat(postRepository.findAllNotSold().size()).isEqualTo(2);
    }

    @Test
    public void findAllPostsByUser() {
        User user1 = new User("Ivan", "123");
        User user2 = new User("Sergey", "123");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        List<Post> posts = List.of(
                new Post("test1", user1),
                new Post("test2", user1),
                new Post("test3", user2)
        );
        posts.forEach(postRepository::addPost);
        assertThat(postRepository.findAllByUser(user1).size()).isEqualTo(2);
    }

    @Test
    public void findPostsAllByLastDay() {
        Post post1 = new Post("test1", LocalDateTime.now());
        Post post2 = new Post("test2", LocalDateTime.now().minusDays(2));
        postRepository.addPost(post1);
        postRepository.addPost(post2);
        assertThat(postRepository.findByLastDay().get(0).getText()).isEqualTo("test1");
    }

    @Test
    public void findPostsWithPhoto() {
        byte[] arr = new byte[10];
        List<Post> posts = List.of(
                new Post("test1", arr),
                new Post("test2")
        );
        posts.forEach(postRepository::addPost);
        assertThat(postRepository.findWithPhoto().get(0).getPhoto()).isNotNull();
    }

}