package com.msg;

import com.enterprise.project.entity.Project;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class RespProjectDetailMsg {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("项目哈希")
    private String projectHash;
    @ApiModelProperty("发布人哈希")
    private String publisherHash;
    @ApiModelProperty("资产总数")
    private Integer numberOfAssets;
    @ApiModelProperty("市值")
    private BigDecimal marketValue;
    @ApiModelProperty("电站类型")
    private String powerStationType;
    @ApiModelProperty("地理位置")
    private String address;
    @ApiModelProperty("装机容量")
    private BigDecimal installedCapacity;
    @ApiModelProperty("累计发电量")
    private BigDecimal cumulativePowerGeneration;
    @ApiModelProperty("预计年发电收入")
    private BigDecimal forecastAnnualIncome;
    @ApiModelProperty("上网电价")
    private BigDecimal fullPrice;
    @ApiModelProperty("产权期限(年)")
    private Integer propertyRightTerm;
    @ApiModelProperty("已正常发电（天）")
    private Long powerDays;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("七日发电量")
    private Map<String,BigDecimal> oneWeekOfPowerGeneration;
    @ApiModelProperty("所在区块高度")
    private Long blockHeight;
    @ApiModelProperty("项目状态")
    private String status;
    @ApiModelProperty("上链时间")
    private String upChainDate;
    @ApiModelProperty("交易数量")
    private Long tradeComponentNo = 0L;
    @ApiModelProperty("天平链存证地址")
    private String chainPath;

    public RespProjectDetailMsg() {
    }

    public RespProjectDetailMsg(Long id, String projectName, String projectHash, String publisherHash, Integer numberOfAssets, BigDecimal marketValue, String powerStationType, String address, BigDecimal installedCapacity, BigDecimal forecastAnnualIncome, BigDecimal fullPrice, Integer propertyRightTerm, Date createTime, Date mergeDate, BigDecimal cumulativePowerGeneration, Map<String, BigDecimal> oneWeekOfPowerGeneration, Long blockHeight,Long upChainDate,String chainPath) {
        this.id = id;
        this.projectName = projectName;
        this.projectHash = projectHash;
        this.publisherHash = publisherHash;
        this.numberOfAssets = numberOfAssets;
        this.marketValue = marketValue;
        this.powerStationType = powerStationType;
        this.address = address;
        this.installedCapacity = installedCapacity;
        this.forecastAnnualIncome = forecastAnnualIncome;
        this.fullPrice = fullPrice;
        this.propertyRightTerm = propertyRightTerm;
        this.createTime = TimeUtils.dateToString(createTime);
        this.powerDays = TimeUtils.calcToNowDays(mergeDate)<0L?0L:TimeUtils.calcToNowDays(mergeDate);
        this.cumulativePowerGeneration = cumulativePowerGeneration;
        this.oneWeekOfPowerGeneration = oneWeekOfPowerGeneration;
        this.blockHeight = blockHeight;
        this.upChainDate = TimeUtils.stampToTimeyMdHms(upChainDate);
        this.chainPath = chainPath;
    }

    public static RespProjectDetailMsg convert(Project project,Map<String,BigDecimal> oneWeekOfPowerGeneration) {
        return new RespProjectDetailMsg(project.getId(),project.getName(),project.getAssetHash(),project.getPublisherHash(),project.getNumberOfAssets(),project.getMarketValue(),project.getPowerStationType(),project.getAddress(),project.getInstalledCapacity(),project.getForecastAnnualIncome(),project.getFullPrice(),project.getPropertyRightTerm(),project.getCreateTime(),project.getMergeDate(),project.getCumulativePowerGeneration(),oneWeekOfPowerGeneration,project.getBlockHeight(),project.getUpChainDate(),project.getChainPath());
    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectHash() {
        return projectHash;
    }

    public String getPublisherHash() {
        return publisherHash;
    }

    public Integer getNumberOfAssets() {
        return numberOfAssets;
    }

    public BigDecimal getMarketValue() {
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

    public Long getPowerDays() {
        return powerDays;
    }

    public String getCreateTime() {
        return createTime;
    }

    public BigDecimal getCumulativePowerGeneration() {
        return cumulativePowerGeneration;
    }

    public Map<String, BigDecimal> getOneWeekOfPowerGeneration() {
        return oneWeekOfPowerGeneration;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public String getStatus() {
        return status;
    }

    public String getUpChainDate() {
        return upChainDate;
    }

    public Long getTradeComponentNo() {
        return tradeComponentNo;
    }

    public String getChainPath() {
        return chainPath;
    }
}
