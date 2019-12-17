package com.msg;

import com.supernode.entity.SuperNode;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

public class RespSuperNodeListMsg {
    @ApiModelProperty("联盟节点id")
    private Long id;
    @ApiModelProperty("联盟节点名称")
    private String name;
    @ApiModelProperty("联盟节点国家")
    private String country;
    @ApiModelProperty("联盟节点加入时间")
    private String joinTime;
    @ApiModelProperty("联盟节点状态")
    private String superNodeStatus;

    public RespSuperNodeListMsg() {
    }

    public RespSuperNodeListMsg(Long id, String name, String country, String joinTime,String superNodeStatus) {
        this.id = id;
        this.name = name;
        this.country = this.convertToGBK(country);
        this.joinTime = joinTime;
        this.superNodeStatus = superNodeStatus;
    }

    private String convertToGBK(String country) {
        switch (country){
            case "China":
                return "中国";
            case "HongKong":
                return "中国香港";
        }
        return null;
    }

    public static RespSuperNodeListMsg convert(SuperNode superNode){
        return new RespSuperNodeListMsg(superNode.getId(),superNode.getName(),superNode.getCountry(), TimeUtils.stampToTimeyMd(superNode.getJoinTime()),superNode.getSuperNodeStatus().getContent());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public String getSuperNodeStatus() {
        return superNodeStatus;
    }
}
