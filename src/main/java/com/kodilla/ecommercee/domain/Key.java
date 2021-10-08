package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accessKeys")

public class Key {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "accessKey")
    private String accessKey;

    @Column(name = "expirationTime")
    private LocalDateTime expirationTime;

    @OneToOne(mappedBy = "key")
    private User user;
}