package com.supernode.entity;

public enum  SuperNodeStatus {
    RUN(0,"正常"),
    DONE(1,"挂了")
    ;

    Integer index;
    String content;

    SuperNodeStatus(Integer index, String content) {
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
