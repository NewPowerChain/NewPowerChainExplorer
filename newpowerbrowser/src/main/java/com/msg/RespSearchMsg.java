package com.msg;

import com.block.entity.Block;
import com.block.tx.entity.Tx;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.reptile.BlockChainFeignClient;
import com.user.service.UserService;
import io.swagger.annotations.ApiModelProperty;

public class RespSearchMsg {
    @ApiModelProperty("查询到的信息类型/(区块,交易,节点)")
    private RespSearchType searchType;
    @ApiModelProperty("查询到的区块信息")
    private RespBlockDetailsMsg blockDetailsMsg;
    @ApiModelProperty("查询到的交易信息")
    private RespTxDetailsMsg txDetailsMsg;
    @ApiModelProperty("查询到的节点信息")
    private RespEnterpriseDetailMsg enterpriseDetailMsg;

    public RespSearchMsg() {
    }

    public RespSearchMsg(Block block, Tx tx, Enterprise enterprise, UserService userService, ProjectService projectService, ContractService contractService, BlockChainFeignClient blockChainFeignClient, EnterpriseService enterpriseService) {
        if (block != null) {
            this.searchType = RespSearchType.BLOCK;
            this.blockDetailsMsg = RespBlockDetailsMsg.convert(block,enterpriseService);
        }
        if (tx != null) {
            this.searchType = RespSearchType.TX;
            this.txDetailsMsg = RespTxDetailsMsg.convert(tx,userService,enterpriseService,projectService,contractService);
        }
        if (enterprise != null) {
            this.searchType = RespSearchType.ENTERPRISE;
            this.enterpriseDetailMsg = RespEnterpriseDetailMsg.convert(enterprise);
        }
    }

    public RespSearchType getSearchType() {
        return searchType;
    }

    public RespBlockDetailsMsg getBlockDetailsMsg() {
        return blockDetailsMsg;
    }

    public RespTxDetailsMsg getTxDetailsMsg() {
        return txDetailsMsg;
    }

    public RespEnterpriseDetailMsg getEnterpriseDetailMsg() {
        return enterpriseDetailMsg;
    }
}
