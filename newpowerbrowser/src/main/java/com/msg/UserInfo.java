package com.msg;

import io.swagger.annotations.ApiModelProperty;

class UserInfo {
    @ApiModelProperty("播报方")
    String broadcaster;
    @ApiModelProperty("用户")
    String user;

    public UserInfo() {
    }

    public UserInfo(String broadcaster, String user) {
        this.broadcaster = broadcaster;
        this.user = user;
    }

    public String getBroadcaster() {
        return broadcaster;
    }

    public String getUser() {
        return user;
    }
}
