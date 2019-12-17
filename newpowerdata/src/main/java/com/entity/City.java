package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "CITY")
public class City {
    @GeneratedValue
    @Id
    Long id ;
    @Column(name = "PROVINCE_CODE")
    String provinceCode;
    String code;
    String name;

    public Long getId() {
        return id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
