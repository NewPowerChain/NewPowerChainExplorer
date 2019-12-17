package com.msg;

public class RespTxCountMsg {

    private Long totalCount;
    private Long evidenceCount;
    private Long contractCount;

    public RespTxCountMsg() {
    }

    public RespTxCountMsg(Long totalCount, Long evidenceCount, Long contractCount) {
        this.totalCount = totalCount;
        this.evidenceCount = evidenceCount;
        this.contractCount = contractCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Long getEvidenceCount() {
        return evidenceCount;
    }

    public Long getContractCount() {
        return contractCount;
    }
}
