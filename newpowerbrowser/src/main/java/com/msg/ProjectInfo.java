package com.msg;

import io.swagger.annotations.ApiModelProperty;

class ProjectInfo {
    @ApiModelProperty("项目名称")
    String name;
    @ApiModelProperty("项目hash")
    String hash;

    public ProjectInfo() {
    }

    public ProjectInfo(String name, String hash) {
        this.name = name;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }
}
