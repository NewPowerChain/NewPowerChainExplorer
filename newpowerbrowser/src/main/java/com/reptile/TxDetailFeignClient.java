package com.reptile;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "NEWPOWERBROWSERDATA", url = "http://localhost:9932", configuration = FeignEncoderConfig.class)
//@FeignClient(value = "newpowerdata", configuration = FeignEncoderConfig.class)
public interface TxDetailFeignClient {

    @RequestMapping(value = "/newpowerdata/getCompanyInfo/{enterpriseHash}", method = RequestMethod.GET)
    JSONObject getCompanyInfo(@PathVariable(name = "enterpriseHash") String enterpriseHash);

    @RequestMapping(value = "/newpowerdata/getProjectInfo/{assetHash}", method = RequestMethod.GET)
    JSONObject getProjectInfo(@PathVariable(name = "assetHash") String assetHash);

}
