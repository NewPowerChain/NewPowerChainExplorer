package com.enterprise.project.repository;

import com.enterprise.project.contract.entity.Contract;
import com.msg.IsUpdate;
import com.enterprise.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Page<Project> findByIsUpdate(Pageable pageable, IsUpdate isUpdate);

    Project findByName(String photovoltaicName);

    Project findByAssetHash(String assetHash);

    List<Project> findByIsUpdate(IsUpdate isUpdate);

    Long countByIsUpdate(IsUpdate isUpdate);

    Project findByContractsContains(Contract contract);

    @Query(value ="select sum(installedCapacity) from Project ")
    BigDecimal getSumOfInstalledCapacity();

    Project findByTxId(String txId);
}
