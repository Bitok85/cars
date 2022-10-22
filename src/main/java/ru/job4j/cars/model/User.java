package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "auto_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @EqualsAndHashCode.Exclude
    private String login;
    @EqualsAndHashCode.Exclude
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_user_id")
    private List<PriceHistory> priceHistories;

    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = {@JoinColumn(name = "auto_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "auto_post_id")}
    )
    private List<Post> participates;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
