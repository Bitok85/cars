package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String text;
    private int price;
    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    private byte[] photo;
    private boolean sold;
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Post(String text, boolean sold, User user, Car car) {
        this.text = text;
        this.sold = sold;
        this.user = user;
        this.car = car;
    }

    public Post(int id, String text, byte[] photo, boolean sold, User user, Car car) {
        this.id = id;
        this.text = text;
        this.photo = photo;
        this.sold = sold;
        this.user = user;
        this.car = car;
    }

    public Post(String text, byte[] photo, boolean sold, User user, Car car) {
        this.text = text;
        this.photo = photo;
        this.sold = sold;
        this.user = user;
        this.car = car;
    }

    public Post(String text, boolean sold) {
        this.text = text;
        this.sold = sold;
    }

    public Post(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Post(String text, LocalDateTime created) {
        this.text = text;
        this.created = created;
    }

    public Post(String text, byte[] photo) {
        this.text = text;
        this.photo = photo;
    }

    public Post(String text) {
        this.text = text;
    }
}
