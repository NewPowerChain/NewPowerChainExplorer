package com.msg;

import io.swagger.annotations.ApiModelProperty;

public class SystemInfo {
    @ApiModelProperty("系统名")
    private String name;
    @ApiModelProperty("动作")
    private String action;

    public SystemInfo() {
    }

    public SystemInfo(String name) {
        this.name = name;
        this.action = "部署";
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }
}
