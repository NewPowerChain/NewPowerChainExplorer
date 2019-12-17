package com.msg;

import io.swagger.annotations.ApiModelProperty;

class EnterpriseInfo {
    @ApiModelProperty("企业名称")
    String name;
    @ApiModelProperty("企业hash")
    String hash;

    public EnterpriseInfo() {
    }

    public EnterpriseInfo(String name, String hash) {
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
