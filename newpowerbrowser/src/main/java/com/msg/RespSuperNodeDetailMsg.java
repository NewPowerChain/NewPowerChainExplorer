package com.msg;

import com.supernode.entity.SuperNode;
import com.utils.TimeUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class RespSuperNodeDetailMsg {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("联盟节点名称")
    private String name;
    @ApiModelProperty("联盟节点ip")
    private String host;
    @ApiModelProperty("联盟节点端口")
    private String port;
    @ApiModelProperty("联盟节点操作系统")
    private String os;
    @ApiModelProperty("联盟节点国家地区")
    private String country;
    @ApiModelProperty("联盟节点加入时间")
    private String joinTime;
    @ApiModelProperty("联盟节点id")
    private String nodeId;
    @ApiModelProperty("联盟节点Node")
    private String enode;
    @ApiModelProperty("一周内出块统计")
    private Map<String,Long> oneWeekCountOfBlock;

    public RespSuperNodeDetailMsg() {
    }

    public RespSuperNodeDetailMsg(Long id,String name, String host, String port, String os, String country, String joinTime, String nodeId, String enode) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.port = port;
        this.os = os;
        this.country = convertToGBK(country);
        this.joinTime = joinTime;
        this.nodeId = nodeId;
        this.enode = enode;
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

    public static RespSuperNodeDetailMsg convert(SuperNode superNode){
        return new RespSuperNodeDetailMsg(superNode.getId(),superNode.getName(),superNode.getHost(),superNode.getPort(),superNode.getOs(),superNode.getCountry(), TimeUtils.stampToTimeyMd(superNode.getJoinTime()),superNode.getNodeId(),superNode.getEnode());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getOs() {
        return os;
    }

    public String getCountry() {
        return country;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getEnode() {
        return enode;
    }

    public Map<String, Long> getOneWeekCountOfBlock() {
        return oneWeekCountOfBlock;
    }
}
