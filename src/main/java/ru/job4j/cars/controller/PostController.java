package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;
import ru.job4j.cars.util.UserCheck;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.NoSuchElementException;


@AllArgsConstructor
@Controller
public class PostController {

    public PostService postService;
    public CarService carService;
    public EngineService engineService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("posts", postService.findAllNotSold());
        return "index";
    }

    @GetMapping("/account")
    public String account(Model model, HttpSession session) {
        model.addAttribute("posts", postService.findAllByUser(UserCheck.defineUser(session)));
        return "account";
    }

    @GetMapping("/addPost")
    public String addPost(Model model, HttpSession session) {
        model.addAttribute("engines", engineService.findAll());
        return "addPost";
    }

    @PostMapping("createPost")
    public String createPost(@ModelAttribute Post post,
                             @ModelAttribute Car car,
                             @ModelAttribute Engine engine,
                             @RequestParam ("file") MultipartFile file) throws IOException {
        car.setEngine(engineService.findByTypeAndVol(engine.getType(), engine.getVol())
                .orElseThrow(() -> new NoSuchElementException("Engine not found")));
        post.setCar(car);
        post.setPhoto(file.getBytes());
        post.setSold(false);
        carService.addCar(car);
        postService.addPost(post);
        return "redirect:/account";
    }



}
