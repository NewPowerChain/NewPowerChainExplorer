package com.supernode.entity;

import javax.persistence.*;

@Entity
public class SuperNode {

    @GeneratedValue
    @Id
    Long id;
    String name;
    String host;
    String port;
    String os;
    String country;
    Long joinTime;
    String nodeId;
    String enode;
    @Enumerated(EnumType.STRING)
    SuperNodeStatus superNodeStatus = SuperNodeStatus.RUN;

    public SuperNode() {
    }

    public SuperNode(String name, String host, String port, String os, String country, Long joinTime, String nodeId, String enode) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.os = os;
        this.country = country;
        this.joinTime = joinTime;
        this.nodeId = nodeId;
        this.enode = enode;
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

    public Long getJoinTime() {
        return joinTime;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getEnode() {
        return enode;
    }

    public SuperNodeStatus getSuperNodeStatus() {
        return superNodeStatus;
    }
}
