package com.enterprise.project.entity;

public enum ProjectStatus {

    AUDIT_PASS(1,"审核通过");

    Integer index;
    String content;

    ProjectStatus(Integer index, String content) {
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
