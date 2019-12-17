package com.msg;

import com.entity.LegalPerson;
import com.entity.Project;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RespProjectInfoMsg {
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("项目编号")
    private String code;
    @ApiModelProperty("天秤链存证地址")
    private String chainPath;
    @ApiModelProperty("发布公司哈希")
    private String publisherHash;
    @ApiModelProperty("电站类型")
    private String powerStationType;
    @ApiModelProperty("地理位置")
    private String address;
    @ApiModelProperty("装机容量")
    private BigDecimal installedCapacity;
    //    @ApiModelProperty("累计发电量")
    @ApiModelProperty("预计年发电收入")
    private BigDecimal forecastAnnualIncome;
    @ApiModelProperty("上网电价")
    private BigDecimal fullPrice;
    @ApiModelProperty("产权期限(年)")
    private Integer propertyRightTerm;
    @ApiModelProperty("已正常发电(天)")
    private Date mergeDate;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("组件数量")
    private Integer assemblyCount;
    @ApiModelProperty("注册资金")
    private BigDecimal financingAmount;
    @ApiModelProperty("法人")
    private String legalPersonName;
    @ApiModelProperty("审核日期")
    private Date auditDate;

    public RespProjectInfoMsg() {
    }

    public RespProjectInfoMsg(String name, String code, String chainPath, String publisherHash, String powerStationType, String address, BigDecimal installedCapacity, BigDecimal forecastAnnualIncome, BigDecimal fullPrice, Integer propertyRightTerm, Integer assemblyCount, Integer assemblyCountOther, BigDecimal financingAmount, Date mergeDate, Date createTime, String pcAddress, List<LegalPerson> legalPersons, Date auditDate) {
        //直接删除后面位数 不做进位处理
        this.name = pcAddress + getInstalledCapacityString(installedCapacity) + this.convertToType(powerStationType);
        this.code = code;
        this.chainPath = chainPath;
        this.publisherHash = publisherHash;
        this.powerStationType = this.convertToType(powerStationType);
        this.address = address;
        this.installedCapacity = installedCapacity;
        this.forecastAnnualIncome = forecastAnnualIncome;
        this.fullPrice = fullPrice;
        this.propertyRightTerm = propertyRightTerm;
        this.assemblyCount = assemblyCount;
        if (this.assemblyCount == null) {
            this.assemblyCount = assemblyCountOther;
        }
        this.financingAmount = financingAmount;
        this.createTime = createTime;
        this.mergeDate = mergeDate;
        this.auditDate = auditDate;
        for (LegalPerson legalPerson : legalPersons) {
            if (legalPerson.getType().equals("legalPerson")) {
                this.legalPersonName = legalPerson.getName();
            }
            if (legalPerson.getType().equals("agent")) {
                if (legalPerson.getName() != null && legalPerson.getName().length() != 0) {
                    this.legalPersonName = legalPerson.getName();
                }
            }
        }

    }

    private String getInstalledCapacityString(BigDecimal installedCapacity) {
        if (installedCapacity.compareTo(new BigDecimal(1)) == -1) {
            return installedCapacity.multiply(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_DOWN) + "KW";
        }
        return installedCapacity.setScale(2, BigDecimal.ROUND_DOWN) + "MW";
    }

    private String convertToType(String powerStationType) {
        switch (powerStationType) {
            case "1":
                return "工商业分布式电站";
            case "2":
                return "山地集中式电站";
            case "3":
                return "地面集中式电站";
            case "4":
                return "矿坑集中式电站";
            case "5":
                return "滩涂集中式电站";
            case "6":
                return "水上集中式电站";
        }
        return "";
    }

    public static RespProjectInfoMsg convert(Project project, String pcAddress, String address, Integer assemblyCount, List<LegalPerson> legalPerson) {
        return new RespProjectInfoMsg(project.getName(), project.getCode(), project.getChainPath(),
                null, project.getPowerStationType(),
                address, project.getInstalledCapacity(), project.getForecastAnnualIncome(),
                project.getFullPrice(), project.getPropertyRightTerm(), project.getAssemblyCount(), assemblyCount, project.getFinancingAmount(), project.getMergeDate(), project.getCreateTime(), pcAddress, legalPerson, project.getAuditDate());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getChainPath() {
        return chainPath;
    }

    public String getPublisherHash() {
        return publisherHash;
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

    public Integer getAssemblyCount() {
        return assemblyCount;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public Date getMergeDate() {
        return mergeDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public Date getAuditDate() {
        return auditDate;
    }
}
