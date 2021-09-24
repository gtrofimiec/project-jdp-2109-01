package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "groups")
public class group {

    @Id
    @GeneratedValue
    private long group_id;

    @Column(name = "name")
    private String name;
}
