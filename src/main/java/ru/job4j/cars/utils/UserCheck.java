package ru.job4j.cars.utils;

import ru.job4j.cars.model.User;

import javax.servlet.http.HttpSession;

public final class UserCheck {

    private UserCheck() {
    }

    public static User defineUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }
}
