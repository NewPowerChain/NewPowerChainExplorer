package com.msg;

import io.swagger.annotations.ApiModelProperty;

public class ReqSuperNodeTxListMsg extends ReqPagingMsg{
    @ApiModelProperty("联盟节点id")
    private Long id ;
    @ApiModelProperty("联盟节点名称")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
