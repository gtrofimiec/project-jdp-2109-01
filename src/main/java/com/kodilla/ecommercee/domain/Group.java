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
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private long id;

    @Column(name = "name")
    private String name;
}
