package com.msg;

import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.reptile.BlockChainFeignClient;
import com.user.service.UserService;
import com.utils.TimeUtils;
import com.block.tx.entity.Tx;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RespTxDetailsMsg {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("交易哈希")
    private String txHash;
    @ApiModelProperty("天枰链背书存证地址")
    private String chainPath;
    @ApiModelProperty("交易类型")
    private String txType;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("交易结果")
    private String txResult;
    @ApiModelProperty("当前交易所在的区块")
    private Long blockNumber;
    @ApiModelProperty("当前交易生成的时间")
    private String time;
    @ApiModelProperty("当前交易生成时间的时间戳")
    private Long timestamp;
    @ApiModelProperty("操作者信息")
    private TxOperatorInfo txOperatorInfo;
    @ApiModelProperty("操作类型")
    private String operationType;
    @ApiModelProperty("费用")
    private BigDecimal cost;


    public RespTxDetailsMsg() {
    }

    public RespTxDetailsMsg(Tx tx, UserService userService, EnterpriseService enterpriseService, ProjectService projectService, ContractService contractService) {
        this.id = tx.getId();
        this.txHash = tx.getTxId();
        this.txType = tx.getTxType().getContent();
        this.txResult = tx.getTxResult().getContent();
        this.blockNumber = tx.getBlockNumber();
        this.time = TimeUtils.stampToTimeyMdHms(tx.getTimestamp());
        this.timestamp = tx.getTimestamp();
        this.chainPath = tx.getChainPath();
        this.operationType = tx.getOperationType().getOperationType();
        this.cost = tx.getCost();
        this.content = tx.getOperationType().getContent();
        this.txOperatorInfo = new TxOperatorInfo(tx, userService, enterpriseService, projectService,contractService);
    }

    public static RespTxDetailsMsg convert(Tx tx, UserService userService, EnterpriseService enterpriseService, ProjectService projectService, ContractService contractService) {
        return new RespTxDetailsMsg(tx, userService, enterpriseService, projectService,contractService);
    }

    public Long getId() {
        return id;
    }

    public String getTxHash() {
        return txHash;
    }

    public String getChainPath() {
        return chainPath;
    }

    public String getTxType() {
        return txType;
    }

    public String getContent() {
        return content;
    }

    public String getTxResult() {
        return txResult;
    }

    public Long getBlockNumber() {
        return blockNumber;
    }

    public String getTime() {
        return time;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public TxOperatorInfo getTxOperatorInfo() {
        return txOperatorInfo;
    }

    public String getOperationType() {
        return operationType;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
