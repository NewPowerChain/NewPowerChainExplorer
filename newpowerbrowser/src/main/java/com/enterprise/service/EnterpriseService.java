package com.enterprise.service;

import com.alibaba.fastjson.JSONObject;
import com.msg.*;
import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.block.tx.service.TxService;
import com.enterprise.entity.*;
import com.enterprise.project.entity.Project;
import com.enterprise.repository.EnterpriseRepository;
import com.evidence.entity.EvidenceType;
import com.evidence.service.EvidenceService;
import com.reptile.TxDetailFeignClient;
import com.searchcondition.entity.ConditionType;
import com.searchcondition.service.HomePageService;
import com.user.service.UserService;
import com.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class EnterpriseService {

    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    EvidenceService evidenceService;
    @Autowired
    UserService userService;
    @Autowired
    TxService txService;
    @Autowired
    TxDetailFeignClient txDetailFeignClient;
    @Autowired
    HomePageService homePageService;


    public void createEnterprise(String enterpriseType, String evidenceHash, String evidenceFileName, String evidenceFileSize, String founderHash, String enterpriseHash, Tx tx) {
//        User founder = userService.findByUserHash(founderHash);
        //创建企业
        Enterprise enterprise = createEnterpriseWithType(enterpriseType, enterpriseHash, founderHash, tx.getTxId(),tx.getTimestamp());
        if (enterprise == null) {
            return;
        }
        homePageService.createSearchCondition(enterpriseHash, ConditionType.ENTERPRISE);
        //更新企业相关信息
        JSONObject companyInfo = txDetailFeignClient.getCompanyInfo(enterpriseHash);
        if (companyInfo != null && companyInfo.getString("name") != null) {
            enterprise.updateEnterprise(companyInfo.getString("name"), companyInfo.getString("profile"), companyInfo.getString("code"));
            tx.setIsUpdate(IsUpdate.TRUE);
            tx.setChainPath(companyInfo.getString("chainPath"));
            enterprise.setChainPath(companyInfo.getString("chainPath"));
            tx.setCallDataHash(enterpriseHash);
        }
        //添加创建企业相关的存证
        evidenceService.createEvidence(enterpriseHash, evidenceHash, evidenceFileName, evidenceFileSize, EvidenceType.CREATE_ENTERPRISE);
        //更新交易信息
        tx.updateTxInfo(OperationType.CREATE_ENTERPRISE, enterpriseHash, null);

        enterpriseRepository.save(enterprise);
    }

    private Enterprise createEnterpriseWithType(String enterpriseType, String enterpriseHash, String founderHash, String txId, Long upChainDate) {
        switch (enterpriseType) {
            case "asset_side":
                return new AssetEnterprise(enterpriseHash, founderHash, txId,upChainDate);
            case "fund_side":
                return new FinanceEnterprise(enterpriseHash, founderHash, txId,upChainDate);
            case "service":
                return new DedicatedEnterprise(enterpriseHash, founderHash, txId,upChainDate);
            case "endorsement_side":
                return new EndorsementEnterprise(enterpriseHash, founderHash, txId,upChainDate);
            default:
                return null;
        }
    }

    public void updateEnterprise(String enterpriseHash, String evidenceHash, String evidenceFileName, String evidenceFileSize, Tx tx) {
        Enterprise enterprise = this.findByEnterpriseHash(enterpriseHash);
        JSONObject companyInfo = txDetailFeignClient.getCompanyInfo(enterpriseHash);
        if (companyInfo != null && companyInfo.getString("name") != null) {
            enterprise.updateEnterprise(companyInfo.getString("name"), companyInfo.getString("profile"), companyInfo.getString("code"));
            tx.setIsUpdate(IsUpdate.TRUE);
            tx.setChainPath(companyInfo.getString("chainPath"));
            enterprise.setChainPath(companyInfo.getString("chainPath"));
            tx.setCallDataHash(enterpriseHash);
        }
        evidenceService.createEvidence(evidenceHash, evidenceHash, evidenceFileName, evidenceFileSize, EvidenceType.UPDATE_ENTERPRISE);
        tx.updateTxInfo(OperationType.UPDATE_ENTERPRISE, enterprise.getEnterpriseHash(), null);
    }

    public Enterprise findByEnterpriseHash(String enterpriseHash) {
        return enterpriseRepository.findByEnterpriseHash(enterpriseHash);
    }

    public Enterprise findByProjectsContains(Project project) {
        return enterpriseRepository.findByProjectsContains(project);
    }

    public RespEnterpriseDetailMsg getEnterpriseDetailByEnterpriseHash(String enterpriseHash) {
        Enterprise enterprise = enterpriseRepository.findByEnterpriseHash(enterpriseHash);
        String chainPath = txService.findByTxId(enterprise.getTxId()).getChainPath();
        return RespEnterpriseDetailMsg.convert(enterprise);
    }

    public Page<RespTxListMsg> getEnterpriseTxList(ReqEnterpriseTxMsg reqEnterpriseTxMsg) {
        Page<Tx> txPage = txService.findBySenderOrReceiver(reqEnterpriseTxMsg.getEnterpriseHash(), reqEnterpriseTxMsg.getPageRequest());
        Page<RespTxListMsg> map = txPage.map(tx -> RespTxListMsg.convert(tx,this.findByEnterpriseHash(tx.getSender()),this.findByEnterpriseHash(tx.getReceiver())));
        return map;
    }

    public Page<RespEnterpriseListMsg> getEnterPriseList(PageRequest pageRequest) {
        Page<Enterprise> enterprisePage = enterpriseRepository.findByIsUpdate(pageRequest, IsUpdate.TRUE);
        Page<RespEnterpriseListMsg> map = enterprisePage.map(RespEnterpriseListMsg::convert);
        return map;
    }

    public RespEnterpriseCountMsg getEnterpriseCountInfo() {
        Long nowEnterPriseCount = enterpriseRepository.countByIsUpdate(IsUpdate.TRUE);
        List<OperationType> operationTypes = new ArrayList<>();
        operationTypes.add(OperationType.CREATE_ENTERPRISE);
        operationTypes.add(OperationType.UPDATE_ENTERPRISE);
        Long count = txService.countByIsUpdateAndOperationTypeIn(IsUpdate.TRUE, operationTypes);
        Map<String, Long> oneWeekEnterpriseEvidence = new LinkedHashMap<>();
        oneWeekEnterpriseEvidence.put(TimeUtils.getMdDateString(-24 * 7), txService.countOperationTypeInAndTimestampBetween(operationTypes, TimeUtils.dayTimeInMillis() - 86400L * 7L, TimeUtils.dayTimeInMillis() - 86400L * 6L - 1L));
        oneWeekEnterpriseEvidence.put(TimeUtils.getMdDateString(-24 * 6), txService.countOperationTypeInAndTimestampBetween(operationTypes, TimeUtils.dayTimeInMillis() - 86400L * 6L, TimeUtils.dayTimeInMillis() - 86400L * 5L - 1L));
        oneWeekEnterpriseEvidence.put(TimeUtils.getMdDateString(-24 * 5), txService.countOperationTypeInAndTimestampBetween(operationTypes, TimeUtils.dayTimeInMillis() - 86400L * 5L, TimeUtils.dayTimeInMillis() - 86400L * 4L - 1L));
        oneWeekEnterpriseEvidence.put(TimeUtils.getMdDateString(-24 * 4), txService.countOperationTypeInAndTimestampBetween(operationTypes, TimeUtils.dayTimeInMillis() - 86400L * 4L, TimeUtils.dayTimeInMillis() - 86400L * 3L - 1L));
        oneWeekEnterpriseEvidence.put(TimeUtils.getMdDateString(-24 * 3), txService.countOperationTypeInAndTimestampBetween(operationTypes, TimeUtils.dayTimeInMillis() - 86400L * 3L, TimeUtils.dayTimeInMillis() - 86400L * 2L - 1L));
        oneWeekEnterpriseEvidence.put(TimeUtils.getMdDateString(-24 * 2), txService.countOperationTypeInAndTimestampBetween(operationTypes, TimeUtils.dayTimeInMillis() - 86400L * 2L, TimeUtils.dayTimeInMillis() - 86400L - 1L));
        oneWeekEnterpriseEvidence.put(TimeUtils.getMdDateString(-24), txService.countOperationTypeInAndTimestampBetween(operationTypes, TimeUtils.dayTimeInMillis() - 86400L, TimeUtils.dayTimeInMillis() - 1L));

        return new RespEnterpriseCountMsg(nowEnterPriseCount, count, oneWeekEnterpriseEvidence);
    }

    public Enterprise findByTxId(String txId) {
        return enterpriseRepository.findByTxId(txId);
    }
}
