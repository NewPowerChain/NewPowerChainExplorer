package com.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @GeneratedValue
    @Id
    private Long id;

    private String userHash;
    private String tel;

    public User() {
    }

    public User(String userHash, String tel) {
        this.userHash = userHash;
        this.tel = tel;
    }

    public String getUserHash() {
        return userHash;
    }

    public String getTel() {
        return tel;
    }
}
