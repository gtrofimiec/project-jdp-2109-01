package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name="key")
    private String key;

    @Column (name="expirationDate")
    private LocalDateTime expirationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public User(String firstName, String surname, String key, LocalDateTime expirationDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.key = key;
        this.expirationDate = expirationDate;
    }
}