package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> addPost(Post post) {
        return postRepository.addPost(post);
    }

    public boolean updatePost(Post post) {
        return postRepository.updatePost(post);
    }

    public boolean deletePost(int id) {
        return postRepository.deletePost(id);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public List<Post> findAllNotSold() {
        return postRepository.findAllNotSold();
    }

    public List<Post> findAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    public List<Post> findByLastDay() {
        return postRepository.findByLastDay();
    }

    public List<Post> findWithPhoto() {
        return postRepository.findWithPhoto();
    }

    public List<Post> findByBrand(String brand) {
        return postRepository.findByBrand(brand);
    }
}
