package com.enterprise.project.contract.entity;

import javax.persistence.*;

@Entity
public class Contract {

    @GeneratedValue
    @Id
    private Long id;
    //合约的创建者
    private String creator;
    //合约的接收者
    private String executor;
    //合约地址
    private String contractHash;
    //执行
//    @Enumerated(value = EnumType.STRING)
    private String result;

    private String txId;

    private String contractType;

    public Contract() {
    }

    public Contract(String contractType,String creator, String executor, String contractHash,String txId) {
        this.creator = creator;
        this.executor = executor;
        this.contractHash = contractHash;
        this.txId = txId;
        this.contractType = contractType;
    }

    public Long getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public String getExecutor() {
        return executor;
    }

    public String getContractHash() {
        return contractHash;
    }

    public void updateResult(String operation) {
       this.result = operation;
    }

    public String getResult() {
        return result;
    }

    public String getTxId() {
        return txId;
    }

    public String getContractType() {
        return contractType;
    }
}
