package com.enterprise.project.contract.repository;

import com.enterprise.project.contract.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByContractHash(String contractHash);

    Contract findByTxId(String txId);
}
