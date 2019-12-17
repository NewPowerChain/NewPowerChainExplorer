package com.enterprise.project.service;

import com.alibaba.fastjson.JSONObject;
import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.block.tx.service.TxService;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.contract.entity.Contract;
import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.entity.Project;
import com.enterprise.project.generationcapacity.repository.DailyPowerGenerationRepository;
import com.enterprise.project.repository.ProjectRepository;
import com.enterprise.service.EnterpriseService;
import com.evidence.entity.EvidenceType;
import com.evidence.service.EvidenceService;
import com.msg.*;
import com.reptile.BlockChainFeignClient;
import com.reptile.TxDetailFeignClient;
import com.user.service.UserService;
import com.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class ProjectService {

    @Autowired
    EvidenceService evidenceService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TxService txService;
    @Autowired
    TxDetailFeignClient txDetailFeignClient;
    @Autowired
    DailyPowerGenerationRepository dailyPowerGenerationRepository;
    @Autowired
    ContractService contractService;

    private Logger logger = LoggerFactory.getLogger(ProjectService.class);


    public Project createAsset(String evidenceHash, String evidenceFileName, String evidenceFileSize, String enterpriseHash, String assetHash, Tx tx) {
        Enterprise enterprise = enterpriseService.findByEnterpriseHash(enterpriseHash);
        JSONObject projectInfo = txDetailFeignClient.getProjectInfo(assetHash);
        Project project = new Project(enterpriseHash, assetHash, tx.getTxId(), tx.getBlockNumber(),tx.getTimestamp());
        if (projectInfo != null && projectInfo.getString("name") != null) {
            logger.info(projectInfo.toString());
            project = new Project(projectInfo.getString("name"), enterpriseHash, projectInfo.getInteger("assemblyCount"), projectInfo.getBigDecimal("financingAmount"), projectInfo.getString("powerStationType"), projectInfo.getString("address"), projectInfo.getBigDecimal("installedCapacity"), projectInfo.getBigDecimal("forecastAnnualIncome"), projectInfo.getBigDecimal("fullPrice"), projectInfo.getInteger("propertyRightTerm"), assetHash, projectInfo.getString("code"), projectInfo.getDate("mergeDate"), projectInfo.getDate("createTime"), tx.getTxId(), projectInfo.getString("legalPersonName"), tx.getBlockNumber(),projectInfo.getDate("auditDate"),tx.getTimestamp());
            tx.setChainPath(projectInfo.getString("chainPath"));
            project.setChainPath(projectInfo.getString("chainPath"));
            tx.setIsUpdate(IsUpdate.TRUE);
            tx.setCallDataHash(assetHash);
        }
        enterprise.addProjectToEnterprise(project);
        evidenceService.createEvidence(enterpriseHash, evidenceHash, evidenceFileName, evidenceFileSize, EvidenceType.CREATE_ASSET);
        tx.updateTxInfo(OperationType.CREATE_ASSET, enterpriseHash, null);
        return projectRepository.save(project);
    }

    public void updateAsset(String evidenceHash, String evidenceFileName, String evidenceFileSize, String assetHash, Tx tx) {
        Project project = projectRepository.findByAssetHash(assetHash);
        JSONObject projectInfo = txDetailFeignClient.getProjectInfo(assetHash);
        if (projectInfo != null && projectInfo.getString("name") != null) {
            logger.info(projectInfo.toString());
            project.update(projectInfo.getString("name"), projectInfo.getInteger("assemblyCount"), projectInfo.getBigDecimal("financingAmount"), projectInfo.getString("powerStationType"), projectInfo.getString("address"), projectInfo.getBigDecimal("installedCapacity"), projectInfo.getBigDecimal("forecastAnnualIncome"), projectInfo.getBigDecimal("fullPrice"), projectInfo.getInteger("propertyRightTerm"), projectInfo.getString("code"), projectInfo.getDate("mergeDate"), projectInfo.getDate("createTime"), projectInfo.getString("legalPersonName"),projectInfo.getDate("auditDate"));
            tx.setIsUpdate(IsUpdate.TRUE);
            tx.setCallDataHash(assetHash);
            tx.setChainPath(projectInfo.getString("chainPath"));
            project.setChainPath(projectInfo.getString("chainPath"));
        }
        Enterprise enterprise = enterpriseService.findByProjectsContains(project);
        evidenceService.createEvidence(enterprise.getEnterpriseHash(), evidenceHash, evidenceFileName, evidenceFileSize, EvidenceType.UPDATE_ASSET);
        tx.updateTxInfo(OperationType.UPDATE_ASSET, enterprise.getEnterpriseHash(), null);
    }

    public Project findByAssetHash(String assetHash) {
        return projectRepository.findByAssetHash(assetHash);
    }

    public Page<RespProjectListMsg> getProjectDetail(PageRequest pageRequest) {
        Page<Project> projectPage = projectRepository.findByIsUpdate(pageRequest, IsUpdate.TRUE);
        return projectPage.map(RespProjectListMsg::convert);
    }

    public RespProjectDetailMsg getProjectDetail(String projectHash) {
        Project project = projectRepository.findByAssetHash(projectHash);
        Map<String, BigDecimal> oneWeekOfPowerGeneration = new LinkedHashMap<>();
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 7), dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 7)) == null ? new BigDecimal(0) : dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 7)).getGenerationCapacity());
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 6), dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 6)) == null ? new BigDecimal(0) : dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 6)).getGenerationCapacity());
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 5), dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 5)) == null ? new BigDecimal(0) : dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 5)).getGenerationCapacity());
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 4), dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 4)) == null ? new BigDecimal(0) : dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 4)).getGenerationCapacity());
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 3), dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 3)) == null ? new BigDecimal(0) : dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 3)).getGenerationCapacity());
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 2), dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 2)) == null ? new BigDecimal(0) : dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24 * 2)).getGenerationCapacity());
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24), dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24)) == null ? new BigDecimal(0) : dailyPowerGenerationRepository.findByAssetHashAndDate(project.getAssetHash(), TimeUtils.getDateStart(-24)).getGenerationCapacity());
        return RespProjectDetailMsg.convert(project, oneWeekOfPowerGeneration);
    }

    @Autowired
    UserService userService;

    public Page<RespTxListMsg> getProjectTxList(ReqProjectDetailMsg reqProjectDetailMsg) {
        Project project = projectRepository.findByAssetHash(reqProjectDetailMsg.getProjectHash());
        List<Contract> contracts = project.getContracts();
        List<String> txIds = new ArrayList<>();
        for (Contract contract : contracts) {
            txIds.add(contract.getTxId());
        }
        Page<Tx> txPage = txService.findProjectTx(project,txIds,reqProjectDetailMsg.getPageRequest());
        return txPage.map(tx -> RespTxListMsg.convert(tx, userService, enterpriseService, this, contractService));
    }

    public Long getNowProjectCount() {
        return projectRepository.countByIsUpdate(IsUpdate.TRUE);
    }

    public BigDecimal getSumOfInstalledCapacity() {
        return projectRepository.getSumOfInstalledCapacity();
    }

    public Project findByContractsContains(Contract contract) {
        return projectRepository.findByContractsContains(contract);
    }

    public Project findByTxId(String txId) {
        return projectRepository.findByTxId(txId);
    }

    public RespProjectDataMsg getProjectData() {
        BigDecimal sumOfInstalledCapacity = this.getSumOfInstalledCapacity();
        Long nowProjectCount = this.getNowProjectCount();
        Map<String, BigDecimal> oneWeekOfPowerGeneration = new LinkedHashMap<>();
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 7), dailyPowerGenerationRepository.getOneDayPowerGeneration(TimeUtils.getDateStart(-24 * 7)));
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 6), dailyPowerGenerationRepository.getOneDayPowerGeneration(TimeUtils.getDateStart(-24 * 6)));
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 5), dailyPowerGenerationRepository.getOneDayPowerGeneration(TimeUtils.getDateStart(-24 * 5)));
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 4), dailyPowerGenerationRepository.getOneDayPowerGeneration(TimeUtils.getDateStart(-24 * 4)));
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 3), dailyPowerGenerationRepository.getOneDayPowerGeneration(TimeUtils.getDateStart(-24 * 3)));
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24 * 2), dailyPowerGenerationRepository.getOneDayPowerGeneration(TimeUtils.getDateStart(-24 * 2)));
        oneWeekOfPowerGeneration.put(TimeUtils.getMdDateString(-24), dailyPowerGenerationRepository.getOneDayPowerGeneration(TimeUtils.getDateStart(-24)));
        return new RespProjectDataMsg(sumOfInstalledCapacity, nowProjectCount, oneWeekOfPowerGeneration);
    }
}
