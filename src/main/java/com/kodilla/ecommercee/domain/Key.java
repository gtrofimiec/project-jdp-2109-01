package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "access_keys")
public class Key {

    @Id
    @GeneratedValue
    @Column(name = "key_id")
    private Long id;

    @Column(name = "accessKey")
    private String accessKey;

    @Column(name = "expirationTime")
    private LocalDateTime expirationTime;

    @OneToOne(mappedBy = "key")
    private User user;
}