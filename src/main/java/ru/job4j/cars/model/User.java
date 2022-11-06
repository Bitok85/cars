package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "auto_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
    private String login;

    private String password;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_user_id")
    private List<PriceHistory> priceHistories;

    @ToString.Exclude
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
