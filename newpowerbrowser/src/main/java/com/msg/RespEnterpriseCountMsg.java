package com.msg;

import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class RespEnterpriseCountMsg {
    @ApiModelProperty("企业账户数")
    private Long enterPriseUserCount;
    @ApiModelProperty("企业信息存证数量")
    private Long enterpriseInfoEvidence;
    @ApiModelProperty("企业信息存证统计图")
    private Map<String,Long> oneWeekEnterpriseEvidence;

    public RespEnterpriseCountMsg() {
    }

    public RespEnterpriseCountMsg(Long enterPriseUserCount, Long enterpriseInfoEvidence, Map<String, Long> oneWeekEnterpriseEvidence) {
        this.enterPriseUserCount = enterPriseUserCount;
        this.enterpriseInfoEvidence = enterpriseInfoEvidence;
        this.oneWeekEnterpriseEvidence = oneWeekEnterpriseEvidence;
    }

    public Long getEnterPriseUserCount() {
        return enterPriseUserCount;
    }

    public Long getEnterpriseInfoEvidence() {
        return enterpriseInfoEvidence;
    }

    public Map<String, Long> getOneWeekEnterpriseEvidence() {
        return oneWeekEnterpriseEvidence;
    }
}
