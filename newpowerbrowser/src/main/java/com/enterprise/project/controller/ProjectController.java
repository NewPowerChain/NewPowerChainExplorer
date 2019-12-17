package com.enterprise.project.controller;

import com.enterprise.project.service.ProjectService;
import com.msg.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="光伏项目相关接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @ApiOperation("获取项目列表")
    @PostMapping("/getProjectList")
    public PageDto<RespProjectListMsg> getProjectList(@RequestBody ReqPagingMsg reqPagingMsg){
        return new PageDto<>(projectService.getProjectDetail(reqPagingMsg.getPageRequest()));
    }

    @ApiOperation("根据项目hash获取项目详情")
    @GetMapping("/getProjectDetail/{projectHash}")
    public RespProjectDetailMsg getPhotovoltaicDetail(@PathVariable String projectHash){
        return projectService.getProjectDetail(projectHash);
    }

    @ApiOperation("项目页数据统计接口")
    @PostMapping("/getProjectData")
    public RespProjectDataMsg getProjectData(){
        return projectService.getProjectData();
    }

    @ApiOperation("项目相关交易列表")
    @PostMapping("/getProjectTxList")
    public PageDto<RespTxListMsg> getProjectTxList(@RequestBody ReqProjectDetailMsg reqProjectDetailMsg) {
        return new PageDto<>(projectService.getProjectTxList(reqProjectDetailMsg));
    }
}
