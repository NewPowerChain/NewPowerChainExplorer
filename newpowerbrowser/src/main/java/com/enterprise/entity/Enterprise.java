package com.enterprise.entity;

import com.msg.IsUpdate;
import com.enterprise.project.entity.Project;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EnterpriseType")
public abstract class Enterprise {
    @GeneratedValue
    @Id
    Long id;
    //企业名称
    String name;
    //企业地址的hash
    private String enterpriseHash;
    //公司的创始人
//    @OneToOne
    private String founderHash;
    //公司描述
    @Lob
    @Column(columnDefinition = "text")
    private String enterPriseDescribe;

    @OneToMany(cascade = CascadeType.ALL)
    List<Project> projects;

    private String code;
    @Enumerated(EnumType.STRING)
    IsUpdate isUpdate = IsUpdate.FALSE;

    private String txId;

    private Long upChainDate;

    private String chainPath;


    public Enterprise() {
    }

    public Enterprise(String enterpriseHash, String founderHash, String txId,Long upChainDate) {
        this.enterpriseHash = enterpriseHash;
        this.founderHash = founderHash;
        this.txId = txId;
        this.upChainDate = upChainDate;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public String getEnterpriseHash() {
        return enterpriseHash;
    }

    public void addProjectToEnterprise(Project project) {
        this.projects.add(project);
    }

    public void updateEnterprise(String name, String enterPriseDescribe, String code) {
        this.name = name;
        this.enterPriseDescribe = enterPriseDescribe;
        this.code = code;
        this.isUpdate = IsUpdate.TRUE;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFounderHash() {
        return founderHash;
    }

    public String getEnterPriseDescribe() {
        return enterPriseDescribe;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getMarketValue() {
        BigDecimal bigDecimal = new BigDecimal(0);
        if (this.projects != null && this.projects.size() != 0) {
            for (Project project : this.projects) {
                bigDecimal = bigDecimal.add(project.getMarketValue());
            }
        }

        return bigDecimal;
    }

    public BigDecimal getInstalledCapacity() {
        BigDecimal bigDecimal = new BigDecimal(0);
        if (this.projects != null && this.projects.size() != 0) {
            for (Project project : this.projects) {
                bigDecimal = bigDecimal.add(project.getInstalledCapacity() == null ? new BigDecimal(0) : project.getInstalledCapacity());
            }
        }
        return bigDecimal;
    }

    public String getTxId() {
        return txId;
    }

    public IsUpdate getIsUpdate() {
        return isUpdate;
    }

    public Long getUpChainDate() {
        return upChainDate;
    }

    public String getChainPath() {
        return chainPath;
    }

    public void setChainPath(String chainPath) {
        this.chainPath = chainPath;
    }
}


