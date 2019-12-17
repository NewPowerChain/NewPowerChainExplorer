package com.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PROJECT")
public class Project {
    @GeneratedValue
    @Id
    Long id;
    @ApiModelProperty("项目所属公司id")
    @Column(name = "COMPANY_ID")
    Long companyId;
    @ApiModelProperty("项目编号")
    String code;
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("天秤链存证地址")
    @Column(name = "CHAIN_PATH")
    private String chainPath;
    @ApiModelProperty("发布公司哈希")
    private String developer;
    @ApiModelProperty("电站类型")
    @Column(name = "POWER_STATION_TYPE")
    private String powerStationType;
    @ApiModelProperty("地理位置")
    private String province;
    private String city;
    private String address;
    @ApiModelProperty("装机容量")
    @Column(name = "INSTALLED_CAPACITY")
    private BigDecimal installedCapacity;
    //        @ApiModelProperty("累计发电量")
    @ApiModelProperty("预计年发电收入")
    @Column(name = "FORECAST_ANNUAL_INCOME")
    private BigDecimal forecastAnnualIncome;
    @ApiModelProperty("上网电价")
    @Column(name = "FULL_PRICE")
    private BigDecimal fullPrice;
    @ApiModelProperty("产权期限(年)")
    @Column(name = "PROPERTY_RIGHT_TERM")
    private Integer propertyRightTerm;
    @ApiModelProperty("并网时间")
    @Column(name = "MERGE_DATE")
    private Date mergeDate;
    @ApiModelProperty("组件数量")
    @Column(name = "ASSEMBLY_COUNT")
    private Integer assemblyCount;
    @Column(name = "CHAIN_ADDRESS")
    private String projectHash;
    @Column(name = "FINANCING_AMOUNT")//注册资金
    private BigDecimal financingAmount;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "FLAG")
    private Integer flag;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "AUDIT_DATE")
    private Date auditDate;

    public Long getCompanyId() {
        return companyId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getChainPath() {
        return chainPath;
    }

    public String getPowerStationType() {
        return powerStationType;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
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

    public String getDeveloper() {
        return developer;
    }

    public String getCode() {
        return code;
    }

    public String getProjectHash() {
        return projectHash;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public Integer getFlag() {
        return flag;
    }

    public Date getMergeDate() {
        return mergeDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Date getAuditDate() {
        return auditDate;
    }
}
