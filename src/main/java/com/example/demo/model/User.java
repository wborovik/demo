package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "date_create")
    private LocalDate dateCreateUser;
    @OneToMany(mappedBy = "user")
    private List<Quote> quotes;

    public User() {
        this.dateCreateUser = LocalDate.now();
    }

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

}
