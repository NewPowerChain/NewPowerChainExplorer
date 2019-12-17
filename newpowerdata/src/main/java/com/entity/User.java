package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @GeneratedValue
    @Id
    Long id ;
    @Column(name = "MOBILE")
    private String mobile;

    public Long getId() {
        return id;
    }
}
