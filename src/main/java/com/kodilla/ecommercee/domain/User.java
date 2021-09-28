package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "Firstname")
    private String firstName;

    @Column(name = "Surname")
    private String surname;

    @Column(name="loggingTime")
    private LocalDateTime loggingTime;

    @Column (name="isBlocked")
    private boolean isBlocked;

//    uncomment when Cart Class is available!
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "cart_id")
//    private Cart cart;
}