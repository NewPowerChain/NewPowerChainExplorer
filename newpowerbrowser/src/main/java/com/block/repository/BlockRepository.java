package com.block.repository;

import com.block.entity.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {

    Page<Block> findAll(Pageable pageable);

    Block findBlockByBlockHeight(String blockHeight);

//    @Query(value ="select count(*) from block" ,nativeQuery = true)
//    Long getBlockHeight();

    Block findByBlockHash(String blockHash);

    Block findByBlockHeight(String blockHeight);
}
