package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "LEGAL_PERSON")
public class LegalPerson {
    @GeneratedValue
    @Id
    Long id ;
    @Column(name = "NAME")
    String name;
    @Column(name = "TYPE")
    String type;
    @Column(name = "COMPANY_ID")
    Long companyId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getCompanyId() {
        return companyId;
    }
}
