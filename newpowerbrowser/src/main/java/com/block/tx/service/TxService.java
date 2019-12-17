package com.block.tx.service;

import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.block.tx.entity.TxType;
import com.block.tx.repository.TxRepository;
import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.entity.Project;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.msg.IsUpdate;
import com.msg.RespTxCountMsg;
import com.msg.RespTxDetailsMsg;
import com.msg.RespTxListMsg;
import com.reptile.BlockChainFeignClient;
import com.supernode.entity.SuperNode;
import com.supernode.repository.SuperNodeRepository;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TxService {

    @Autowired
    TxRepository txRepository;
    @Autowired
    SuperNodeRepository superNodeRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    ContractService contractService;
    @Autowired
    BlockChainFeignClient blockChainFeignClient;

    public Tx addTx(Long blockNumber, Integer sizeOfTx, Long timestamp, String txId) {
        SuperNode superNode = superNodeRepository.findById(1L).get();
        Tx tx = new Tx(blockNumber, sizeOfTx, timestamp, txId,superNode);
        return txRepository.save(tx);
    }

    public Page<RespTxListMsg> getTxList(PageRequest pageRequest) {
        Page<Tx> txPage = txRepository.findByIsUpdate(pageRequest, IsUpdate.TRUE);
        Page<RespTxListMsg> map = txPage.map(tx -> RespTxListMsg.convert(tx,userService,enterpriseService,projectService,contractService));
        return map;
    }

    public RespTxDetailsMsg getTxDetail(String txId) {
        return RespTxDetailsMsg.convert(txRepository.findByTxId(txId),userService,enterpriseService,projectService,contractService);
    }

    public Tx findByTxId(String searchCondition) {
        return txRepository.findByTxId(searchCondition);
    }

    public Page<Tx> findBySenderOrReceiver(String publisherHash, PageRequest pageRequest) {
        return txRepository.findBySenderOrReceiver(publisherHash, publisherHash, pageRequest);
    }

    public Long getNowTxCount() {
        return txRepository.countByIsUpdate(IsUpdate.TRUE);
    }

    public Long getOneDayTxByTxType(List<TxType> txTypes, Long startTime, Long endTime) {
        return txRepository.countByTxTypeInAndIsUpdateAndTimestampBetween(txTypes, IsUpdate.TRUE, startTime, endTime);
    }

    public RespTxCountMsg getTxCount() {
        Long totalCount = this.getNowTxCount();
        Long evidenceCount = txRepository.countByTxTypeInAndIsUpdate(TxType.getDifferentTxType("存证"),IsUpdate.TRUE);
        Long contractCount = txRepository.countByTxTypeInAndIsUpdate(TxType.getDifferentTxType("合约"),IsUpdate.TRUE);
        return new RespTxCountMsg(totalCount,evidenceCount,contractCount);
    }

    public Long countByIsUpdateAndOperationTypeIn(IsUpdate isUpdate, List<OperationType> operationTypes) {
        return txRepository.countByIsUpdateAndOperationTypeIn(isUpdate,operationTypes);
    }

    public Long countOperationTypeInAndTimestampBetween(List<OperationType> operationTypes, Long startTime, Long endTime){
        return txRepository.countByIsUpdateAndOperationTypeInAndTimestampBetween(IsUpdate.TRUE,operationTypes,startTime,endTime);
    }

    public Page<Tx> findProjectTx(Project project, List<String> txIds,PageRequest pageRequest) {
        return txRepository.findByCallDataHashOrTxIdIn(project.getAssetHash(),txIds,pageRequest);
    }
}
