package com.block.tx.entity;


import java.util.ArrayList;
import java.util.List;

public enum TxType {

    CONTRACT(0, "调用合约", "合约"),
    INFORMATION_EXISTING_EVIDENCE(1, "信息存证", "存证"),
    TRADE_EXISTING_EVIDENCE(2, "交易存证", "存证"),
    INVOICE_EXISTING_EVIDENCE(3, "电网结算发票存证", "存证"),
    SYSTEM_DEPLOY(4,"系统部署","系统")
    ;

    Integer index;
    String content;
    String type;

    public static List<TxType> getDifferentTxType(String type) {
        List<TxType> txTypes = new ArrayList<>();
        for (TxType txType : TxType.values()) {
            if (type.equals(txType.getType())) {
                txTypes.add(txType);
            }
        }
        return txTypes;
    }

    public static TxType getTxType(OperationType operationType) {
        switch (operationType) {
            case CREATE_USER:
            case UPDATE_ENTERPRISE:
            case CREATE_ENTERPRISE:
            case CREATE_ASSET:
            case UPDATE_ASSET:
                return INFORMATION_EXISTING_EVIDENCE;
            case UPDATE_DAILY_POWER_GENERATION:
                return INVOICE_EXISTING_EVIDENCE;
            case CREATE_DATA_CONTRACT:
            case CREATE_SERVICE_CONTRACT:
            case CREATE_BUSINESS_CONTRACT:
            case EXECUTE_CONTRACT:
                return CONTRACT;
            case SYSTEM_DEPLOY:
                return SYSTEM_DEPLOY;
            default:
                return null;
        }
    }

    TxType(Integer index, String content, String type) {
        this.index = index;
        this.content = content;
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}


