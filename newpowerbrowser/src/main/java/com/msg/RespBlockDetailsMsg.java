package com.msg;

import com.enterprise.service.EnterpriseService;
import com.utils.TimeUtils;
import com.block.entity.Block;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

public class RespBlockDetailsMsg {
    @ApiModelProperty("当前区块的高度")
    private String blockHeight;
    @ApiModelProperty("当前区块的生成时间的时间戳")
    private Long timeStamp;
    @ApiModelProperty("当前区块的生成时间")
    private String time;
    @ApiModelProperty("当前区块的哈希")
    private String blockHash;
    @ApiModelProperty("当前区块的父块的哈希")
    private String previous_block_hash;
    @ApiModelProperty("当前区块中的交易的笔数")
    private Integer numbersOfTx;
    @ApiModelProperty("当前区块的大小")
    private Integer sizeOfBlock;
    //    @ApiModelProperty("当前查询的区块的区块头大小")
    //    private Integer sizeOfBlockHeader;
    @ApiModelProperty("当前区块中所包含的所有交易")
    private List<RespTxListMsg> txs;

    public RespBlockDetailsMsg() {
    }

    public RespBlockDetailsMsg(String blockHash, String blockHeight, Integer numbersOfTx, String previousBlockHash, Integer sizeOfBlock, Integer sizeOfBlockHeader, Long timeStamp, List<RespTxListMsg> txs) {
        this.blockHash = blockHash;
        this.blockHeight = blockHeight;
        this.numbersOfTx = numbersOfTx;
        this.previous_block_hash = previousBlockHash;
        this.sizeOfBlock = sizeOfBlock;
//        this.sizeOfBlockHeader = sizeOfBlockHeader;
        this.time = TimeUtils.stampToTimeyMdHms(timeStamp);
        this.timeStamp = timeStamp;
        this.txs = txs;
    }

    public static RespBlockDetailsMsg convert(Block block, EnterpriseService enterpriseService) {
        return new RespBlockDetailsMsg(block.getBlockHash(),block.getBlockHeight(),block.getNumbersOfTx(),block.getPrevious_block_hash(),block.getSizeOfBlock(),block.getSizeOfBlockHeader(), block.getTimeStamp(),block.getTxs()==null?null:block.getTxs().stream().map(tx -> RespTxListMsg.convert(tx,enterpriseService.findByEnterpriseHash(tx.getSender()),enterpriseService.findByEnterpriseHash(tx.getReceiver()))).collect(Collectors.toList()));
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getBlockHeight() {
        return blockHeight;
    }

    public Integer getNumbersOfTx() {
        return numbersOfTx;
    }

    public String getPrevious_block_hash() {
        return previous_block_hash;
    }

    public Integer getSizeOfBlock() {
        return sizeOfBlock;
    }

//    public Integer getSizeOfBlockHeader() {
//        return sizeOfBlockHeader;
//    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public List<RespTxListMsg> getTxs() {
        return txs;
    }

    public String getTime() {
        return time;
    }
}
