package com.msg;

import com.block.tx.entity.Tx;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.user.service.UserService;
import com.utils.StringUtils;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RespTxListMsg {
    @ApiModelProperty("交易哈希")
    String txId;
    @ApiModelProperty("类型 存证/合约 用于前端判断跳转")
    String type;
    @ApiModelProperty("交易类型")
    String txType;
    @ApiModelProperty("交易内容")
    String content;
    @ApiModelProperty("费用")
    BigDecimal cost;
    @ApiModelProperty("生成交易的时间")
    String time;
    @ApiModelProperty("生成交易的时间戳")
    Long timeStamp;
    @ApiModelProperty("交易操作者信息")
    TxOperatorInfo txOperatorInfo;
    @ApiModelProperty("操作类型")
    String operationType;
    @ApiModelProperty("区块高度")
    Long blockHeight;
    @ApiModelProperty("发送方")
    EnterpriseInfo sender;
    @ApiModelProperty("接收方")
    EnterpriseInfo receiver;

    public RespTxListMsg() {
    }


    public RespTxListMsg(Tx tx) {
        this.txId = tx.getTxId();
        this.type = tx.getTxType().getType();
        this.txType = tx.getTxType().getContent();
        this.content = tx.getOperationType().getContent();
        this.cost = tx.getCost();
        this.time = TimeUtils.stampToTimeyMdHms(tx.getTimestamp());
        this.timeStamp = tx.getTimestamp();
    }

    public RespTxListMsg(Tx tx,Enterprise sender,Enterprise receiver) {
        this.txId = tx.getTxId();
        this.type = tx.getTxType().getType();
        this.txType = tx.getTxType().getContent();
        this.content = tx.getOperationType().getContent();
        this.cost = tx.getCost();
        this.time = TimeUtils.stampToTimeyMdHms(tx.getTimestamp());
        this.timeStamp = tx.getTimestamp();
        this.blockHeight = tx.getBlockNumber();
        if(sender != null){
            this.sender = new EnterpriseInfo(StringUtils.hideCruxInfo(sender.getName()),sender.getEnterpriseHash());

        }
        if (receiver != null){
            this.receiver = new EnterpriseInfo(StringUtils.hideCruxInfo(receiver.getName()),receiver.getEnterpriseHash());
        }
    }

    public RespTxListMsg(Tx tx, UserService userService, EnterpriseService enterpriseService, ProjectService projectService, ContractService contractService) {
        this.txId = tx.getTxId();
        this.type = tx.getTxType().getType();
        this.txType = tx.getTxType().getContent();
        this.content = tx.getOperationType().getContent();
        this.cost = tx.getCost();
        this.time = TimeUtils.stampToTimeyMdHms(tx.getTimestamp());
        this.timeStamp = tx.getTimestamp();
        this.operationType = tx.getOperationType().getOperationType();
        this.txOperatorInfo = new TxOperatorInfo(tx, userService, enterpriseService, projectService,contractService);
    }

    public static RespTxListMsg convert(Tx tx,Enterprise sender,Enterprise receiver) {
        return new RespTxListMsg(tx,sender,receiver);
    }

    public static RespTxListMsg convert(Tx tx) {
        return new RespTxListMsg(tx);
    }

    public static RespTxListMsg convert(Tx tx, UserService userService, EnterpriseService enterpriseService, ProjectService projectService, ContractService contractService) {
        return new RespTxListMsg(tx, userService, enterpriseService, projectService,contractService);
    }

    public String getTxId() {
        return txId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getTime() {
        return time;
    }

    public String getTxType() {
        return txType;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public TxOperatorInfo getTxOperatorInfo() {
        return txOperatorInfo;
    }

    public String getOperationType() {
        return operationType;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public EnterpriseInfo getSender() {
        return sender;
    }

    public EnterpriseInfo getReceiver() {
        return receiver;
    }
}