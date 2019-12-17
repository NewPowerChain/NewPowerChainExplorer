package com.supernode.controller;

import com.msg.*;
import com.msg.RespSuperNodeDetailMsg;
import com.msg.RespSuperNodeListMsg;
import com.supernode.service.SuperNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "联盟成员节点相关接口")
@RequestMapping(value = "/superNode")
public class SuperNodeController {

    @Autowired
    SuperNodeService superNodeService;

    @PostMapping("/getSuperNodeList")
    @ApiOperation("获取联盟节点列表")
    public List<RespSuperNodeListMsg> getSuperNodeList(){
        return superNodeService.getSuperNodeList();
    }

    @GetMapping("/getSuperNodeDetail/{id}")
    @ApiOperation("获取联盟节点详情")
    public RespSuperNodeDetailMsg getSuperNodeDetail(@PathVariable Long id ){
        return superNodeService.getSuperNodeDetail(id);
    }

    @GetMapping("/getSuperNodeDetailBySuperNodeName/{superNodeName}")
    public RespSuperNodeDetailMsg getSuperNodeDetailBySuperNodeName(@PathVariable String superNodeName ){
        return superNodeService.getSuperNodeDetailByName(superNodeName);
    }
}
