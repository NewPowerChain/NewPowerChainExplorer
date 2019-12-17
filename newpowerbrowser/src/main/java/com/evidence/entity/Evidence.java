package com.evidence.entity;

import javax.persistence.*;

@Entity
public class Evidence {

    @GeneratedValue
    @Id
    Long id;
    //存证的单位的hash
    private String creatorEvidenceHash;
    //存证的文件的hash
    private String evidenceHash;
    //存证的文件名
    private String evidenceFileName;
    //存证的文件size‬
    private String evidenceFileSize;
    //存证的类型
    @Enumerated(value = EnumType.STRING)
    private EvidenceType evidenceType;

    public Evidence() {
    }

    public Evidence(String creatorEvidenceHash,String evidenceHash, String evidenceFileName, String evidenceFileSize, EvidenceType evidenceType) {
        this.creatorEvidenceHash = creatorEvidenceHash;
        this.evidenceHash = evidenceHash;
        this.evidenceFileName = evidenceFileName;
        this.evidenceFileSize = evidenceFileSize;
        this.evidenceType = evidenceType;
    }
}
