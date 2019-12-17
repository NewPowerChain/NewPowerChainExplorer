package com.msg;

import com.enterprise.entity.Enterprise;
import com.enterprise.project.entity.Project;
import com.utils.StringUtils;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

public class RespEnterpriseDetailMsg {
    @ApiModelProperty("节点名称")
    private String enterpriseName;
    @ApiModelProperty("节点介绍")
    private String enterpriseDescribe;
    @ApiModelProperty("节点类型")
    private String enterpriseType;
    @ApiModelProperty("节点哈希")
    private String enterpriseHash;
    @ApiModelProperty("光伏资产组数")
    private Integer numberOfAsset;
    @ApiModelProperty("光伏项目")
    private List<RespEnterpriseProjectMsg> respEnterpriseProjectMsgs;
    @ApiModelProperty("上链时间")
    private String upChainDate;
    @ApiModelProperty("天平链存证地址")
    private String chainPath;

    public RespEnterpriseDetailMsg() {
    }

    public RespEnterpriseDetailMsg(String enterpriseName, String enterpriseDescribe, String enterpriseType, String enterpriseHash, Integer numberOfAsset, List<RespEnterpriseProjectMsg> respEnterpriseProjectMsgs,Long upChainDate,String chainPath) {
        this.enterpriseName = StringUtils.hideCruxInfo(enterpriseName);
        this.enterpriseDescribe = enterpriseDescribe;
        this.enterpriseType = this.convertEnterPriseTypeToString(enterpriseType);
        this.enterpriseHash = enterpriseHash;
        this.numberOfAsset = numberOfAsset;
        this.respEnterpriseProjectMsgs = respEnterpriseProjectMsgs;
        this.upChainDate = TimeUtils.stampToTimeyMdHms(upChainDate);
        this.chainPath = chainPath;
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

    public static RespEnterpriseDetailMsg convert(Enterprise enterprise){

        return new RespEnterpriseDetailMsg(enterprise.getName(),enterprise.getEnterPriseDescribe(),enterprise.getClass().getSimpleName(),enterprise.getEnterpriseHash(),
                enterprise.getProjects()==null?0:enterprise.getProjects().stream().mapToInt(Project::getNumberOfAssets).sum(),enterprise.getProjects()==null?null:enterprise.getProjects().stream().map(RespEnterpriseProjectMsg::convert).collect(Collectors.toList()),enterprise.getUpChainDate(),enterprise.getChainPath());
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getEnterpriseDescribe() {
        return enterpriseDescribe;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public String getEnterpriseHash() {
        return enterpriseHash;
    }

    public Integer getNumberOfAsset() {
        return numberOfAsset;
    }

    public List<RespEnterpriseProjectMsg> getRespEnterpriseProjectMsgs() {
        return respEnterpriseProjectMsgs;
    }

    public String getUpChainDate() {
        return upChainDate;
    }

    public String getChainPath() {
        return chainPath;
    }
}
