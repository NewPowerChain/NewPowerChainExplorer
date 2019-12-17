package com.block.tx.entity;

import com.msg.IsUpdate;
import com.supernode.entity.SuperNode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Tx {
    @GeneratedValue
    @Id
    private Long id;
    //当前交易所在的区块
    private Long blockNumber;
    //当前交易的大小
    private Integer sizeOfTx;
    //当前交易生成时间的时间戳
    private Long timestamp;
    //当前交易的id
    private String txId;
    //操作类型
    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;
    //交易类型
    @Enumerated(value = EnumType.STRING)
    private TxType txType;
    //发送方
    private String sender;
    //接收方
    private String receiver;
    //交易结果
    @Enumerated(value = EnumType.STRING)
    private TxResult txResult = TxResult.SUCCESS;
    @Enumerated(value = EnumType.STRING)
    private IsUpdate isUpdate = IsUpdate.FALSE;
    //天平链存证地址
    private String chainPath;
    //交易费用
    private BigDecimal cost;
    //播报方
    @ManyToOne(cascade = CascadeType.ALL)
    private SuperNode broadcaster;
    //触发事件的hash 更新企业 更新项目 执行合约
    private String callDataHash;

    public Tx() {
    }

    public Tx(Long blockNumber, Integer sizeOfTx, Long timestamp, String txId,SuperNode superNode) {
        this.blockNumber = blockNumber;
        this.sizeOfTx = sizeOfTx;
        this.timestamp = timestamp;
        this.txId = txId;
        this.cost = new BigDecimal("0");
        this.broadcaster = superNode;
    }

    public Long getId() {
        return id;
    }

    public Long getBlockNumber() {
        return blockNumber;
    }

    public Integer getSizeOfTx() {
        return sizeOfTx;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getTxId() {
        return txId;
    }

    public TxType getTxType() {
        return txType;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public TxResult getTxResult() {
        return txResult;
    }

    public void updateTxInfo(OperationType operationType, String sender, String receiver) {
        this.updateOperationType(operationType);
        this.updateTxType(operationType);
        this.updateSenderAndReceiver(sender, receiver);
    }

    private void updateSenderAndReceiver(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    private void updateOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    private void updateTxType(OperationType operationType) {
        this.txType = TxType.getTxType(operationType);
    }

    public void setIsUpdate(IsUpdate isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setChainPath(String chainPath) {
        this.chainPath = chainPath;
    }

    public IsUpdate getIsUpdate() {
        return isUpdate;
    }

    public String getChainPath() {
        return chainPath;
    }

    public BigDecimal getCost() {
        if (this.cost == null) {
            return new BigDecimal("0");
        }
        return cost;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public SuperNode getBroadcaster() {
        return broadcaster;
    }

    public void setCallDataHash(String callDataHash) {
        this.callDataHash = callDataHash;
    }

    public String getCallDataHash() {
        return callDataHash;
    }
}
