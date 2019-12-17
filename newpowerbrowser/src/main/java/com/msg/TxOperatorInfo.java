package com.msg;

import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.contract.entity.Contract;
import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.entity.Project;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.user.entity.User;
import com.user.service.UserService;
import com.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;

class TxOperatorInfo {
    @ApiModelProperty("操作者类型 user/enterPrise/project/contract")
    String operatorType;

    @ApiModelProperty("项目信息")
    ProjectInfo projectInfo;
    @ApiModelProperty("用户信息")
    UserInfo userInfo;
    @ApiModelProperty("合约信息")
    ContractInfo contractInfo;
    @ApiModelProperty("企业信息")
    EnterpriseInfo enterpriseInfo;
    @ApiModelProperty("系统信息")
    SystemInfo systemInfo;

    public TxOperatorInfo() {
    }


    public TxOperatorInfo(Tx tx, UserService userService, EnterpriseService enterpriseService, ProjectService projectService, ContractService contractService) {
        this.operatorType = OperationType.getOperatorType(tx.getOperationType());
        if (OperationType.getOperatorType(tx.getOperationType()).equals("enterPrise")){
            Enterprise enterprise = enterpriseService.findByEnterpriseHash(tx.getCallDataHash());
            this.enterpriseInfo = new EnterpriseInfo(StringUtils.hideCruxInfo(enterprise.getName()),enterprise.getEnterpriseHash());
        }
        if (OperationType.getOperatorType(tx.getOperationType()).equals("contract")) {
            Contract contract = contractService.findByContractHash(tx.getCallDataHash());
            EnterpriseInfo sender = new EnterpriseInfo(StringUtils.hideCruxInfo(enterpriseService.findByEnterpriseHash(contract.getCreator()).getName()),contract.getCreator());
            EnterpriseInfo receiver = new EnterpriseInfo(StringUtils.hideCruxInfo(enterpriseService.findByEnterpriseHash(contract.getExecutor()).getName()), contract.getExecutor());
            this.contractInfo = new ContractInfo(sender,receiver,contract.getContractType(),tx.getOperationType().getOperationType().equals("createContract")?null:contract.getResult());
        }
        if (OperationType.getOperatorType(tx.getOperationType()).equals("user")) {
            User user = userService.findByUserHash(tx.getCallDataHash());
            this.userInfo = new UserInfo(tx.getBroadcaster().getName(), StringUtils.hideTelInfo(user.getTel()));
        }
        if (OperationType.getOperatorType(tx.getOperationType()).equals("project")) {
            Project project = projectService.findByAssetHash(tx.getCallDataHash());
            this.projectInfo = new ProjectInfo(project.getName(), project.getAssetHash());
        }
        if (OperationType.getOperatorType(tx.getOperationType()).equals("system")) {
            this.systemInfo = new SystemInfo(tx.getSender());
        }
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public ContractInfo getContractInfo() {
        return contractInfo;
    }

    public EnterpriseInfo getEnterpriseInfo() {
        return enterpriseInfo;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }
}
