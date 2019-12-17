package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY")
public class Company {
    @GeneratedValue
    @Id
    Long id ;
    String name;
    @Column(name = "USER_ID")
    Long userId;
    @Column(name = "PROFILE")
    String profile;
    @Column(name = "CHAIN_ADDRESS")
    String companyHash;
    @Column(name = "REGISTERED_CAPITAL")
    String registeredCapital;
    @Column(name = "CODE")
    String code;
    @Column(name = "DEPOSIT_ADDRESS")
    String chainPath;

    public String getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getProfile() {
        return profile;
    }

    public String getCompanyHash() {
        return companyHash;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public String getChainPath() {
        return chainPath;
    }
}
