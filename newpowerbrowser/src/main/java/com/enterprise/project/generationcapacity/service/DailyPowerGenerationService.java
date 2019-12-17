package com.enterprise.project.generationcapacity.service;

import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.entity.Project;
import com.enterprise.project.generationcapacity.entity.DailyPowerGeneration;
import com.enterprise.project.generationcapacity.repository.DailyPowerGenerationRepository;
import com.enterprise.project.repository.ProjectRepository;
import com.enterprise.repository.EnterpriseRepository;
import com.msg.IsUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Transactional
@Service
public class DailyPowerGenerationService {

    @Autowired
    DailyPowerGenerationRepository dailyPowerGenerationRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EnterpriseRepository enterpriseRepository;

    public void updateDailyPowerGeneration(String assetHash, Date date, BigDecimal generationCapacity, Tx tx) {
        DailyPowerGeneration dailyPowerGeneration=dailyPowerGenerationRepository.findByAssetHashAndDate(assetHash,date);
        Project project = projectRepository.findByAssetHash(assetHash);
        Enterprise enterprise = enterpriseRepository.findByProjectsContains(project);
        if (dailyPowerGeneration == null){
            dailyPowerGeneration = new DailyPowerGeneration(tx.getTxId(),assetHash,date,generationCapacity);
            dailyPowerGenerationRepository.save(dailyPowerGeneration);
        }else {
            dailyPowerGeneration.updateGenerationCapacity(generationCapacity);
            //总共拆分为10笔交易，第10笔交易的generationCapacity就是全部的generationCapacity
//            if (dailyPowerGeneration.getFlag() == 10){
                project.updateCumulativePowerGeneration(generationCapacity);
//            }
        }
        tx.updateTxInfo(OperationType.UPDATE_DAILY_POWER_GENERATION,enterprise.getEnterpriseHash(),null);
        tx.setIsUpdate(IsUpdate.TRUE);
        tx.setCallDataHash(project.getAssetHash());
    }
}
