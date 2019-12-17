package com.block.controller;

import com.msg.PageDto;
import com.msg.ReqPagingMsg;
import com.msg.RespBlockDetailsMsg;
import com.msg.RespBlockListMsg;
import com.block.service.BlockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="区块相关接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/block")
public class BlockController {

    @Autowired
    BlockService blockService;

    @PostMapping("/getBlockList")
    public PageDto<RespBlockListMsg> getBlockList(@RequestBody ReqPagingMsg reqPagingMsg){
        return new PageDto<RespBlockListMsg>(blockService.getBlockList(reqPagingMsg.getPageRequest()));
    }

    @GetMapping("/getBlockDetailById/{blockId}")
    public RespBlockDetailsMsg getBlockDetailById(@PathVariable Long blockId){
        return blockService.getBlockDetailById(blockId);
    }

    @GetMapping("/getBlockDetailByBlockHash/{blockHash}")
    public RespBlockDetailsMsg getBlockDetailByBlockHash(@PathVariable String blockHash){
        return blockService.getBlockDetailByBlockHash(blockHash);
    }

    @GetMapping("/getBlockDetailByBlockHeight/{blockHeight}")
    public RespBlockDetailsMsg getBlockDetailByBlockHeight(@PathVariable String blockHeight){
        return blockService.getBlockDetailByBlockHeight(blockHeight);
    }

}
