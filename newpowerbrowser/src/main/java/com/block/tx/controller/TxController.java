package com.block.tx.controller;


import com.msg.*;
import com.block.tx.service.TxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="交易相关接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/tx")
public class TxController {

    @Autowired
    TxService txService;

    @PostMapping("/getTxList")
    @ApiOperation("获取交易列表")
    public PageDto<RespTxListMsg> getTxList(@RequestBody ReqPagingMsg reqPagingMsg){
        return new PageDto<RespTxListMsg>(txService.getTxList(reqPagingMsg.getPageRequest()));
    }

    @GetMapping("/getTxDetail/{txId}")
    @ApiOperation("获取交易详情")
    public RespTxDetailsMsg getTxDetail(@PathVariable String txId){
        return txService.getTxDetail(txId);
    }

    @PostMapping("/getTxCount")
    @ApiOperation("交易列表统计数据")
    public RespTxCountMsg getTxCount(){
        return txService.getTxCount();
    }
}
