package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Where(clause = "deleted = false")
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

    @Column(name = "deleted")
    private boolean deleted;

    @OneToOne(mappedBy = "key")
    private User user;
}