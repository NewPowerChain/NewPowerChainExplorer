package com.enterprise.controller;

import com.enterprise.service.EnterpriseService;
import com.msg.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "节点相关接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/enterprise")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping("/getEnterpriseList")
    @ApiOperation("获取企业列表")
    public PageDto<RespEnterpriseListMsg> getEnterpriseList(@RequestBody ReqPagingMsg reqPagingMsg){
        return new PageDto<>(enterpriseService.getEnterPriseList(reqPagingMsg.getPageRequest()));
    }


    @GetMapping(value = "/getEnterpriseDetail/{enterpriseHash}")
    @ApiModelProperty("根据企业hash查找企业详情")
    public RespEnterpriseDetailMsg getEnterpriseDetail(@PathVariable String enterpriseHash) {
        return enterpriseService.getEnterpriseDetailByEnterpriseHash(enterpriseHash);
    }

    @PostMapping(value = "/getEnterpriseTxList")
    @ApiOperation("企业交易列表")
    public PageDto<RespTxListMsg> getEnterpriseTxList(@RequestBody ReqEnterpriseTxMsg reqEnterpriseTxMsg) {
        return new PageDto<>(enterpriseService.getEnterpriseTxList(reqEnterpriseTxMsg));
    }

    @PostMapping(value = "/getEnterpriseCountInfo")
    @ApiOperation("获取企业统计信息")
    public RespEnterpriseCountMsg getEnterpriseCountInfo() {
        return enterpriseService.getEnterpriseCountInfo();
    }

}
