package com.enterprise.entity;

import com.user.entity.User;

import javax.persistence.Entity;

//背书公司
@Entity
public class EndorsementEnterprise extends Enterprise {

    public EndorsementEnterprise() {
    }

    public EndorsementEnterprise(String enterpriseHash, String founderHash,String txId,Long upChainDate) {
        super(enterpriseHash, founderHash,txId,upChainDate);
    }

}
