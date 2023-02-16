package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    private Long id;
    @Column(name = "quote")
    private String quote;
    @Column(name = "date_create_update")
    private LocalDate dateCreateOrUpdate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
