package com.msg;

import com.entity.Company;
import io.swagger.annotations.ApiModelProperty;

public class RespCompanyInfoMsg {
    @ApiModelProperty("企业名称")
    private String name;
    @ApiModelProperty("企业介绍")
    private String profile;
    @ApiModelProperty("企业编号")
    private String code;
    @ApiModelProperty("天平链存证地址")
    private String chainPath;

    public RespCompanyInfoMsg() {
    }

    public RespCompanyInfoMsg(String name, String profile,String code,String chainPath) {
        this.name = name;
        this.profile = profile;
        this.code = code;
        this.chainPath = chainPath;
    }

    public static RespCompanyInfoMsg convert(Company company){
        if (company==null){
            return new RespCompanyInfoMsg();
        }
        return new RespCompanyInfoMsg(company.getName(),company.getProfile(),company.getCode(),company.getChainPath());
    }

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }

    public String getCode() {
        return code;
    }

    public String getChainPath() {
        return chainPath;
    }
}
