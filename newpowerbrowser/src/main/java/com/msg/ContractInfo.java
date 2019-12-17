package com.msg;

import io.swagger.annotations.ApiModelProperty;

class ContractInfo {
    @ApiModelProperty("发送方")
    EnterpriseInfo sender;
    @ApiModelProperty("接收方")
    EnterpriseInfo receiver;
    @ApiModelProperty("合约类型")
    String type;
    @ApiModelProperty("执行结果")
    String result;

    public ContractInfo() {
    }

    public ContractInfo(EnterpriseInfo sender, EnterpriseInfo receiver, String type, String result) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.result = result;
    }

    public EnterpriseInfo getSender() {
        return sender;
    }

    public EnterpriseInfo getReceiver() {
        return receiver;
    }

    public String getType() {
        return type;
    }

    public String getResult() {
        return result;
    }
}
