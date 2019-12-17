package com.enterprise.project.entity;

import com.msg.IsUpdate;
import com.enterprise.project.contract.entity.Contract;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Project {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    //发布人hash
    private String publisherHash;
    //资产数
    private Integer numberOfAssets = 0;
    //市值(融资金额)
    private BigDecimal marketValue;
    //电站类型
    private String powerStationType;
    //地理位置
    private String address;
    //装机容量
    private BigDecimal installedCapacity;
    //累计发电量
    private BigDecimal cumulativePowerGeneration;
    //预计年发电收入
    private BigDecimal forecastAnnualIncome;
    //上网电价
    private BigDecimal fullPrice;
    //产权期限(年)
    private Integer propertyRightTerm;
    //并网时间
    private Date mergeDate;
    //创建时间
    private Date createTime;
    //项目的哈希
    private String assetHash;
    //合约
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contract> contracts;

    private String code;
    @Enumerated(EnumType.STRING)
    IsUpdate isUpdate = IsUpdate.FALSE;

    private String txId;
    private Long blockHeight;

    private String legalPersonName;
    private Date auditDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    private String chainPath;

    private Long upChainDate;

    public Project() {
    }

    public Project(String name, String publisherHash, Integer numberOfAssets, BigDecimal marketValue, String powerStationType, String address, BigDecimal installedCapacity, BigDecimal forecastAnnualIncome, BigDecimal fullPrice, Integer propertyRightTerm, String assetHash, String code, Date mergeDate, Date createTime, String txId, String legalPersonName, Long blockNumber,Date auditDate,Long upChainDate) {
        this.name = name;
        this.publisherHash = publisherHash;
        this.numberOfAssets = (numberOfAssets == null ? 0 : numberOfAssets);
        this.marketValue = marketValue;
        this.powerStationType = powerStationType;
        this.address = address;
        this.installedCapacity = installedCapacity;
        this.forecastAnnualIncome = forecastAnnualIncome;
        this.fullPrice = fullPrice;
        this.propertyRightTerm = propertyRightTerm;
        this.assetHash = assetHash;
        this.code = code;
        this.isUpdate = IsUpdate.TRUE;
        this.createTime = createTime;
        this.mergeDate = mergeDate;
        this.txId = txId;
        this.legalPersonName = legalPersonName;
        this.blockHeight = blockNumber;
        this.auditDate = auditDate;
        this.upChainDate = upChainDate;
    }

    public Project(String publisherHash, String assetHash, String txId,Long blockHeight,Long upChainDate) {
        this.publisherHash = publisherHash;
        this.assetHash = assetHash;
        this.txId = txId;
        this.blockHeight = blockHeight;
        this.upChainDate = upChainDate;
    }

    public void addContractToAsset(Contract contract) {
        this.contracts.add(contract);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPublisherHash() {
        return publisherHash;
    }

    public Integer getNumberOfAssets() {
        return numberOfAssets;
    }

    public BigDecimal getMarketValue() {
        if (this.marketValue == null) {
            return new BigDecimal("0");
        }
        return marketValue;
    }

    public String getPowerStationType() {
        return powerStationType;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getInstalledCapacity() {
        return installedCapacity;
    }

    public BigDecimal getForecastAnnualIncome() {
        return forecastAnnualIncome;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public Integer getPropertyRightTerm() {
        return propertyRightTerm;
    }

    public String getAssetHash() {
        return assetHash;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public String getCode() {
        return code;
    }

    public Date getMergeDate() {
        return mergeDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getTxId() {
        return txId;
    }

    public void updateCumulativePowerGeneration(BigDecimal dailyPowerGeneration) {
        if (this.cumulativePowerGeneration == null) {
            this.cumulativePowerGeneration = new BigDecimal(0);
        }
        this.cumulativePowerGeneration = this.cumulativePowerGeneration.add(dailyPowerGeneration);
    }

    public BigDecimal getCumulativePowerGeneration() {
        return cumulativePowerGeneration;
    }

    public IsUpdate getIsUpdate() {
        return isUpdate;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void update(String name, Integer numberOfAssets, BigDecimal marketValue, String powerStationType, String address, BigDecimal installedCapacity, BigDecimal forecastAnnualIncome, BigDecimal fullPrice, Integer propertyRightTerm, String code, Date mergeDate, Date createTime, String legalPersonName,Date auditDate) {
        this.name = name;
        this.numberOfAssets = numberOfAssets == null ? 0 : numberOfAssets;
        this.marketValue = marketValue;
        this.powerStationType = powerStationType;
        this.address = address;
        this.installedCapacity = installedCapacity;
        this.forecastAnnualIncome = forecastAnnualIncome;
        this.fullPrice = fullPrice;
        this.propertyRightTerm = propertyRightTerm;
        this.code = code;
        this.isUpdate = IsUpdate.TRUE;
        this.mergeDate = mergeDate;
        this.createTime = createTime;
        this.legalPersonName = legalPersonName;
        this.auditDate = auditDate;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
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
