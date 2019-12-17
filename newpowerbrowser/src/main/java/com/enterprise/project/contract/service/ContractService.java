package com.enterprise.project.contract.service;

import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.contract.entity.Contract;
import com.enterprise.project.contract.repository.ContractRepository;
import com.enterprise.project.entity.Project;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.msg.IsUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ContractService {

    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ContractRepository contractRepository;

    public Contract createContract(String contractType, String creator, String executor, String assetHash, String contractHash, Tx tx) {
        Project project = projectService.findByAssetHash(assetHash);
        Contract contract = new Contract(contractType,creator, executor, contractHash,tx.getTxId());
        tx.setIsUpdate(IsUpdate.TRUE);
        project.addContractToAsset(contract);
        tx.updateTxInfo(getOperationType(contractType), creator, executor);
        tx.setCallDataHash(contractHash);
        return contractRepository.save(contract);
    }

    private OperationType getOperationType(String contractType) {
        if (contractType.equals("data")){
            return OperationType.CREATE_DATA_CONTRACT;
        }
        if (contractType.equals("bussiness")){
            return OperationType.CREATE_BUSINESS_CONTRACT;
        }
        if (contractType.equals("service")){
            return OperationType.CREATE_SERVICE_CONTRACT;
        }
        return null;
    }

    public void executeContract(String creator, String operation, String contractHash, Tx tx) {
        List<Contract> contracts = contractRepository.findByContractHash(contractHash);
        tx.setIsUpdate(IsUpdate.TRUE);
        tx.setCallDataHash(contractHash);
        for (Contract contract : contracts) {
            contract.updateResult(operation);
        }
//        Project project = projectService.findByContractsContains(contracts.get(0));
//        Enterprise enterprise = enterpriseService.findByProjectsContains(project);
        tx.updateTxInfo(OperationType.EXECUTE_CONTRACT, contracts.get(0).getCreator(),contracts.get(0).getExecutor());
    }

    public Contract findByTxId(String txId){
        return contractRepository.findByTxId(txId);
    }

    public Contract findByContractHash(String contractHash) {
        return contractRepository.findByContractHash(contractHash).get(0);
    }
}
