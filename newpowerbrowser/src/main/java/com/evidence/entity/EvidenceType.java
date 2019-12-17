package com.evidence.entity;

public enum EvidenceType {
    CREATE_USER(0,"创建用户"),
    CREATE_ENTERPRISE(1,"创建企业"),
    UPDATE_ENTERPRISE(2,"更新企业"),
    CREATE_ASSET(3,"创建资产"),
    UPDATE_ASSET(4,"更新资产"),
        ;

    Integer index;
    String evidenceType;

    EvidenceType(Integer index, String evidenceType) {
        this.index = index;
        this.evidenceType = evidenceType;
    }

    public Integer getIndex() {
        return index;
    }

    public String getEvidenceType() {
        return evidenceType;
    }
}
