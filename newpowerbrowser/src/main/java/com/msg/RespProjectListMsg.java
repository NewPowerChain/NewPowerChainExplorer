package com.msg;

import com.enterprise.project.entity.Project;
import com.utils.StringUtils;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class RespProjectListMsg {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("项目编号")
    private String code;
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("项目哈希")
    private String projectHash;
    @ApiModelProperty("发布人哈希")
    private String publisherHash;
    @ApiModelProperty("资产数")
    private Integer numberOfAssets;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("所在区块高度")
    private Long blockHeight;
    @ApiModelProperty("发布者")
    private String legalPersonName;
    @ApiModelProperty("上链时间")
    private String upChainDate;

    public RespProjectListMsg() {
    }

    public RespProjectListMsg(Long id, String code, String projectName, String publisherHash, Integer numberOfAssets, String projectHash, Date createTime, String legalPersonName, Long blockHeight,Long upChainDate) {
        this.id = id;
        this.projectName = projectName;
        this.publisherHash = publisherHash;
        this.numberOfAssets = numberOfAssets;
        this.createTime = TimeUtils.dateToString(createTime);
        this.code = code;
        this.projectHash = projectHash;
        this.legalPersonName = StringUtils.hideNameInfo(legalPersonName);
        this.blockHeight = blockHeight;
        this.upChainDate = TimeUtils.stampToTimeyMdHms(upChainDate);
    }

    public static RespProjectListMsg convert(Project project) {
        return new RespProjectListMsg(project.getId(), project.getCode(), project.getName(), project.getPublisherHash(), project.getNumberOfAssets(), project.getAssetHash(),project.getCreateTime(),project.getLegalPersonName(),project.getBlockHeight(),project.getUpChainDate());
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getPublisherHash() {
        return publisherHash;
    }

    public Integer getNumberOfAssets() {
        return numberOfAssets;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getProjectHash() {
        return projectHash;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public String getUpChainDate() {
        return upChainDate;
    }
}
