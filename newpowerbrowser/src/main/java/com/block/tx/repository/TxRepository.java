package com.block.tx.repository;


import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.block.tx.entity.TxType;
import com.msg.IsUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TxRepository extends JpaRepository<Tx, Long> {

    Page<Tx> findByIsUpdate(Pageable pageable, IsUpdate isUpdate);

    Tx findByTxId(String txId);

    Page<Tx> findBySenderOrReceiver(String sender, String receiver, Pageable pageable);

    Page<Tx> findByCallDataHashOrTxIdIn(String callDataHash, List<String> txIds,Pageable pageable);

    Long countByIsUpdate(IsUpdate isUpdate);

    Long countByTxTypeInAndIsUpdateAndTimestampBetween(List<TxType> txType, IsUpdate isUpdate, Long startTime, Long endTime);

    Long countByTxTypeInAndIsUpdate(List<TxType> txType, IsUpdate isUpdate);

    Long countByIsUpdateAndOperationTypeIn(IsUpdate isUpdate, List<OperationType> operationTypes);

    Long countByIsUpdateAndOperationTypeInAndTimestampBetween(IsUpdate isUpdate, List<OperationType> operationTypes, Long startTime, Long endTime);
}
