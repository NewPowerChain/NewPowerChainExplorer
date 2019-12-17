package com.reptile;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "", url = "", configuration = FeignEncoderConfig.class)
public interface BlockChainFeignClient {

    @RequestMapping(value = "/scan/ledger_info", method = RequestMethod.GET)
//    @RequestLine("GET")
    JSONObject getBlockHeight();

    @RequestMapping(value = "/scan/block", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    @RequestLine("POST")
    JSONObject getBlock(Map<String, String> requestParam);

    @RequestMapping(value = "/scan/tx", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    @RequestLine("POST")
    JSONObject getTx(Map<String, String> requestParam);

    @RequestMapping(value = "/scan/tx_args", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    @RequestLine("POST")
    JSONObject getTxArgs(Map<String, String> requestParam);

    @RequestMapping(value = "/new_block", method = RequestMethod.POST)
//    @RequestLine("POST")
    JSONObject createFakeBlock();
}
