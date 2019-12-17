package com.searchcondition.controller;


import com.msg.ReqSearchMsg;
import com.msg.RespCountOfWeekTxType;
import com.msg.RespSearchMsg;
import com.searchcondition.msg.RespCountOfDataMsg;
import com.searchcondition.service.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="搜索相关接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/search")
public class HomePageController {


    @Autowired
    HomePageService homePageService;

    @PostMapping("/searchWindow")
    @ApiOperation("搜索框")
    public RespSearchMsg searchWindow(@RequestBody ReqSearchMsg reqSearchMsg) throws InterruptedException {
        return homePageService.search(reqSearchMsg.getSearchCondition());
    }

    @PostMapping("/countOfData")
    @ApiOperation("首页数据统计")
    public RespCountOfDataMsg getCountOfData(){
        return homePageService.getCountOfData();
    }

    @PostMapping("/countOfWeekTxType")
    @ApiOperation("首页图表")
    public RespCountOfWeekTxType getCountOfWeekTxType(){
        return homePageService.getCountOfWeekTxType();
    }

}
