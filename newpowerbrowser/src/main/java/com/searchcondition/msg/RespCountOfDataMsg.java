package com.searchcondition.msg;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RespCountOfDataMsg {

    @ApiModelProperty("当前区块数量")
    Long nowBlockCount;
    @ApiModelProperty("当前交易数量")
    Long nowTxCount;
    @ApiModelProperty("当前光伏项目数量")
    Long nowProjectCount;
    @ApiModelProperty("装机容量")
    BigDecimal installedCapacity;
    @ApiModelProperty("注册用户数")
    Long nowUserCount;

    public RespCountOfDataMsg() {
    }

    public RespCountOfDataMsg(Long blockCount, Long txCount, Long projectCount) {
        this.nowBlockCount = blockCount;
        this.nowTxCount = txCount;
        this.nowProjectCount = projectCount;
    }

    public RespCountOfDataMsg(Long userCount, Long blockCount, Long txCount, Long projectCount, BigDecimal installedCapacity) {
        this.nowUserCount = userCount;
        this.nowBlockCount = blockCount;
        this.nowTxCount = txCount;
        this.nowProjectCount = projectCount;
        this.installedCapacity = installedCapacity.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public Long getNowUserCount() {
        return nowUserCount;
    }

    public Long getNowBlockCount() {
        return nowBlockCount;
    }

    public Long getNowTxCount() {
        return nowTxCount;
    }

    public Long getNowProjectCount() {
        return nowProjectCount;
    }

    public BigDecimal getInstalledCapacity() {
        return installedCapacity;
    }
}
