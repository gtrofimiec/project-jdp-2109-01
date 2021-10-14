package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "access_keys")
@Where(clause = "deleted = false")
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

    private boolean deleted = false;
}