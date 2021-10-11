package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accessKeys")
@SQLDelete(sql = "UPDATE access_keys SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedKeyFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedKeyFilter", condition = "deleted = :isDeleted")
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

    private boolean deleted = false;
}