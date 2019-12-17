package com.block.tx.entity;

public enum TxResult {
    SUCCESS(0,"成功"),
    FAIL(1,"失败"),
    ;

    Integer index;
    String content;

    TxResult(Integer index, String content) {
        this.index = index;
        this.content = content;
    }

    public Integer getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }
}
