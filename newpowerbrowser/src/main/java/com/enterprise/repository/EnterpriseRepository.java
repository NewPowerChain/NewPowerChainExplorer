package com.enterprise.repository;

import com.msg.IsUpdate;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findByEnterpriseHash(String enterpriseHash);

    Enterprise findByProjectsContains(Project project);

    Page<Enterprise> findByIsUpdate(Pageable pageable, IsUpdate isUpdate);

    List<Enterprise> findByIsUpdate(IsUpdate isUpdate);

    Enterprise findByTxId(String txId);

    Long countByIsUpdate(IsUpdate isUpdate);
}
