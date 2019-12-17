package com.controller;

import com.msg.RespProjectInfoMsg;
import com.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="项目信息接口")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @ApiOperation("查询项目相关信息")
    @GetMapping(value = "/getProjectInfo/{projectHash}")
    public RespProjectInfoMsg getProjectInfo(@PathVariable String projectHash){
        return projectService.getProjectInfo(projectHash);
    }

}
