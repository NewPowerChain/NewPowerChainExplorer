package com.enterprise.entity;

import com.user.entity.User;

import javax.persistence.Entity;

//资产公司
@Entity
public class AssetEnterprise extends Enterprise {

    public AssetEnterprise() {
    }

    public AssetEnterprise(String enterpriseHash, String founderHash,String txId,Long upChainDate) {
        super(enterpriseHash, founderHash,txId,upChainDate);
    }

}
