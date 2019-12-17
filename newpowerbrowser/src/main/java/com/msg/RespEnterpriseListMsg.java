package com.msg;

import com.enterprise.entity.Enterprise;
import com.enterprise.project.entity.Project;
import com.utils.StringUtils;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RespEnterpriseListMsg {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("节点编号")
    private String code;
    @ApiModelProperty("节点地址")
    private String enterpriseHash;
    @ApiModelProperty("节点名称")
    private String enterpriseName;
    @ApiModelProperty("节点类型")
    private String enterpriseType;
    @ApiModelProperty("资产数量")
    private Integer numberOfAssets;
    @ApiModelProperty("装机容量")
    private BigDecimal installedCapacity;
    @ApiModelProperty("上链时间")
    String upChainDate;

    public RespEnterpriseListMsg() {
    }

    public RespEnterpriseListMsg(Long id, String code, String enterpriseHash, String enterpriseName, String enterpriseType, Integer numberOfAssets, BigDecimal installedCapacity,Long upChainDate) {
        this.id = id;
        this.code = code;
        this.enterpriseHash = enterpriseHash;
        this.enterpriseName = StringUtils.hideCruxInfo(enterpriseName);
        this.enterpriseType = this.convertEnterPriseTypeToString(enterpriseType);
        this.numberOfAssets = numberOfAssets;
        this.installedCapacity = installedCapacity;
        this.upChainDate = TimeUtils.stampToTimeyMdHms(upChainDate);
    }

    private String convertEnterPriseTypeToString(String enterpriseType) {
        switch (enterpriseType) {
            case "AssetEnterprise":
                return "资产公司";
            case "FinanceEnterprise":
                return "金融公司";
            case "DedicatedEnterprise":
                return "尽调公司";
            case "EndorsementEnterprise":
                return "背书公司";
        }
        return null;
    }

    public static RespEnterpriseListMsg convert(Enterprise enterprise) {
        return new RespEnterpriseListMsg(enterprise.getId(), enterprise.getCode(), enterprise.getEnterpriseHash(), enterprise.getName(), enterprise.getClass().getSimpleName(),
                enterprise.getProjects() == null || enterprise.getProjects().size() == 0 ? 0 : enterprise.getProjects().stream().mapToInt(Project::getNumberOfAssets).sum(), enterprise.getInstalledCapacity(),enterprise.getUpChainDate());
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getEnterpriseHash() {
        return enterpriseHash;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public Integer getNumberOfAssets() {
        return numberOfAssets;
    }

    public BigDecimal getInstalledCapacity() {
        return installedCapacity;
    }

    public String getUpChainDate() {
        return upChainDate;
    }
}
