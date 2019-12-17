package com.msg;

import com.utils.TimeUtils;
import com.block.entity.Block;
import io.swagger.annotations.ApiModelProperty;

public class RespBlockListMsg {
    @ApiModelProperty("id")
    Long id ;
    @ApiModelProperty("区块高度")
    String blockHeight;
    @ApiModelProperty("生成时间")
    String time;
    @ApiModelProperty("交易笔数")
    Integer numbersOfTx;
    @ApiModelProperty("区块大小")
    Integer sizeOfBlock;
    @ApiModelProperty("生成时间时间戳")
    Long timeStamp;

    public RespBlockListMsg() {
    }

    public RespBlockListMsg(Long id, String blockHeight, Long timeStamp, Integer numbersOfTx,Integer sizeOfBlock) {
        this.id = id;
        this.blockHeight = blockHeight;
        this.time = TimeUtils.stampToTimeyMdHms(timeStamp);
        this.timeStamp = timeStamp;
        this.numbersOfTx = numbersOfTx;
        this.sizeOfBlock = sizeOfBlock;
    }

    public static RespBlockListMsg convert(Block block) {
        return new RespBlockListMsg(block.getId(),block.getBlockHeight(),block.getTimeStamp(),block.getNumbersOfTx(),block.getSizeOfBlock());
    }

    public Long getId() {
        return id;
    }

    public String getBlockHeight() {
        return blockHeight;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public Integer getNumbersOfTx() {
        return numbersOfTx;
    }

    public Integer getSizeOfBlock() {
        return sizeOfBlock;
    }

    public String getTime() {
        return time;
    }
}
