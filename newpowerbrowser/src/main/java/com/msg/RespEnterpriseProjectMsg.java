package com.msg;

import com.enterprise.project.entity.Project;
import com.utils.StringUtils;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

public class RespEnterpriseProjectMsg {
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("项目地址")
    private String projectHash;
    @ApiModelProperty("发布者")
    private String legalPersonName;
    @ApiModelProperty("上链时间")
    private String upChainDate;


    public RespEnterpriseProjectMsg() {
    }

    public RespEnterpriseProjectMsg(String name, String projectHash, String legalPersonName, Long upChainDate) {
        this.name = name;
        this.projectHash = projectHash;
        this.legalPersonName = StringUtils.hideNameInfo(legalPersonName);
        this.upChainDate = TimeUtils.stampToTimeyMdHms(upChainDate);
    }

    public static RespEnterpriseProjectMsg convert(Project project){
        return new RespEnterpriseProjectMsg(project.getName(),project.getAssetHash(),project.getLegalPersonName(),project.getUpChainDate());
    }

    public String getName() {
        return name;
    }

    public String getProjectHash() {
        return projectHash;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public String getUpChainDate() {
        return upChainDate;
    }
}
