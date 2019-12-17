package com.reptile;

import com.alibaba.fastjson.JSONObject;
import com.block.entity.Block;
import com.block.repository.BlockRepository;
import com.block.tx.entity.Tx;
import com.block.tx.repository.TxRepository;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.entity.Project;
import com.enterprise.project.repository.ProjectRepository;
import com.enterprise.repository.EnterpriseRepository;
import com.msg.IsUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UpdateTxService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    TxRepository txRepository;
    @Autowired
    TxDetailFeignClient txDetailFeignClient;
    @Autowired
    BlockRepository blockRepository;

    private Logger logger = LoggerFactory.getLogger(UpdateTxService.class);

    //每10分钟去检查数据
//    @Scheduled(cron = "*/2 * * * * ?")
    public void updateData() {
        List<Enterprise> enterprises = enterpriseRepository.findByIsUpdate(IsUpdate.FALSE);
        for (Enterprise enterprise : enterprises) {
            Tx tx = txRepository.findByTxId(enterprise.getTxId());
            JSONObject companyInfo = txDetailFeignClient.getCompanyInfo(enterprise.getEnterpriseHash());
            if (companyInfo != null && companyInfo.getString("name") != null) {
                enterprise.updateEnterprise(companyInfo.getString("name"), companyInfo.getString("profile"), companyInfo.getString("code"));
                tx.setIsUpdate(IsUpdate.TRUE);
                tx.setCallDataHash(enterprise.getEnterpriseHash());
                Block block = blockRepository.findBlockByBlockHeight(tx.getBlockNumber().toString());
                block.addTxSize();
                tx.setChainPath(companyInfo.getString("chainPath"));
                enterprise.setChainPath(companyInfo.getString("chainPath"));
            }
        }

        List<Project> projects = projectRepository.findByIsUpdate(IsUpdate.FALSE);
        for (Project project : projects) {
            Tx tx = txRepository.findByTxId(project.getTxId());
            JSONObject projectInfo = txDetailFeignClient.getProjectInfo(project.getAssetHash());
            if (projectInfo != null && projectInfo.getString("name") != null) {
                project.update(projectInfo.getString("name"), projectInfo.getInteger("assemblyCount"), projectInfo.getBigDecimal("financingAmount"), projectInfo.getString("powerStationType"), projectInfo.getString("address"), projectInfo.getBigDecimal("installedCapacity"), projectInfo.getBigDecimal("forecastAnnualIncome"), projectInfo.getBigDecimal("fullPrice"), projectInfo.getInteger("propertyRightTerm"), projectInfo.getString("code"),projectInfo.getDate("mergeDate"),projectInfo.getDate("createTime"),projectInfo.getString("legalPersonName"), projectInfo.getDate("auditDate"));
                tx.setIsUpdate(IsUpdate.TRUE);
                tx.setCallDataHash(project.getAssetHash());
                Block block = blockRepository.findBlockByBlockHeight(tx.getBlockNumber().toString());
                block.addTxSize();
                tx.setChainPath(projectInfo.getString("chainPath"));
                project.setChainPath(projectInfo.getString("chainPath"));
            }
        }
    }
}
