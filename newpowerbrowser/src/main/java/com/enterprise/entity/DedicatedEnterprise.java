package com.enterprise.entity;

import com.user.entity.User;

import javax.persistence.Entity;

//尽调公司
@Entity
public class DedicatedEnterprise extends Enterprise {

    public DedicatedEnterprise() {
    }

    public DedicatedEnterprise(String enterpriseHash, String founderHash,String txId,Long upChainDate) {
        super(enterpriseHash,founderHash,txId,upChainDate);
    }

}
