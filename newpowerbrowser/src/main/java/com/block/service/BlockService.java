package com.block.service;

import com.block.entity.Block;
import com.block.repository.BlockRepository;
import com.enterprise.entity.Enterprise;
import com.enterprise.service.EnterpriseService;
import com.msg.RespBlockDetailsMsg;
import com.msg.RespBlockListMsg;
import com.supernode.entity.SuperNode;
import com.supernode.repository.SuperNodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BlockService {

    private final static Logger logger = LoggerFactory.getLogger(BlockService.class);

    @Autowired
    BlockRepository blockRepository;
    @Autowired
    EnterpriseService enterpriseService;

    public Block addBlock(String blockHash, String blockHeight, String previous_block_hash, Integer sizeOfBlock, Integer sizeOfBlockHeader, Long timeStamp){
        Block block = new Block(blockHash,blockHeight,previous_block_hash,sizeOfBlock,sizeOfBlockHeader,timeStamp);
        logger.info(block.toString());
        return blockRepository.save(block);
    }

    public Page<RespBlockListMsg> getBlockList(PageRequest pageRequest){
        Page<Block> blockPage = blockRepository.findAll(pageRequest);
        Page<RespBlockListMsg> map = blockPage.map(RespBlockListMsg::convert);
        return map;
    }

    public RespBlockDetailsMsg getBlockDetailById(Long blockId){
         return RespBlockDetailsMsg.convert(blockRepository.findById(blockId).get(),enterpriseService);
    }

    public Long getDbBlockHeight(){
        return blockRepository.count();
    }

    @Autowired
    SuperNodeRepository superNodeRepository;

    public Block findByBlockHeight(String searchCondition) {
        return blockRepository.findBlockByBlockHeight(searchCondition);
    }

    public RespBlockDetailsMsg getBlockDetailByBlockHash(String blockHash) {
        return RespBlockDetailsMsg.convert(blockRepository.findByBlockHash(blockHash),enterpriseService);
    }

    public RespBlockDetailsMsg getBlockDetailByBlockHeight(String blockHeight) {
        return RespBlockDetailsMsg.convert(blockRepository.findByBlockHeight(blockHeight),enterpriseService);
    }
}
