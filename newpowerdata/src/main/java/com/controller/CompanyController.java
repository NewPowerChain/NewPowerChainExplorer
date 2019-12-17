package com.controller;

import com.msg.RespCompanyInfoMsg;
import com.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "企业信息接口")
@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @ApiOperation("查询企业相关信息")
    @GetMapping(value = "/getCompanyInfo/{companyHash}")
    public RespCompanyInfoMsg getCompanyInfo(@PathVariable String companyHash){
        return companyService.getCompanyInfo(companyHash);
    }
}
