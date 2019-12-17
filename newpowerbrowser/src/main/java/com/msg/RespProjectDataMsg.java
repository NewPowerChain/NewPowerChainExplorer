package com.msg;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Map;

public class RespProjectDataMsg {
    @ApiModelProperty("总装机容量")
    BigDecimal installedCapacity;
    @ApiModelProperty("项目数")
    Long projectCount;
    @ApiModelProperty("发电量统计图")
    Map<String,BigDecimal> oneWeekOfPowerGeneration;

    public RespProjectDataMsg() {
    }

    public RespProjectDataMsg(BigDecimal installedCapacity, Long projectCount, Map<String, BigDecimal> oneWeekOfPowerGeneration) {
        this.installedCapacity = installedCapacity;
        this.projectCount = projectCount;
        this.oneWeekOfPowerGeneration = oneWeekOfPowerGeneration;
    }

    public BigDecimal getInstalledCapacity() {
        return installedCapacity;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public Map<String, BigDecimal> getOneWeekOfPowerGeneration() {
        return oneWeekOfPowerGeneration;
    }
}
