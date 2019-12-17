package com.reptile;

import com.alibaba.fastjson.JSONObject;
import com.block.service.BlockService;
import com.block.tx.service.TxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.net.URISyntaxException;

@Transactional
@Service
//@Import(FeignClientsConfiguration.class)
public class CheckDataService {

    private final static Logger logger = LoggerFactory.getLogger(CheckDataService.class);

    @Autowired
    BlockService blockService;
    @Autowired
    TxService txService;
    @Autowired
    AddDataService addDataService;
    @Autowired
    BlockChainFeignClient blockChainFeignClient;

//    @Autowired
//    public CheckDataService(Decoder decoder, Encoder encoder) {
//        blockChainFeignClient = Feign.builder().encoder(encoder).decoder(decoder)
//                .target(Target.EmptyTarget.create(BlockChainFeignClient.class));
//    }

    //    @Scheduled(cron = "0 05 15 * * ?")

//    Long dbBlockHeight = 11380L;

    @ResponseBody
//    @Scheduled(cron = "*/2 * * * * ?")
    public void compareWithNowBlockHeight() throws URISyntaxException {
        JSONObject blockHeight = blockChainFeignClient.getBlockHeight();
        logger.info(blockHeight.toString());
        Long nowChainHigh = blockHeight.getLong("block_height");
        
        Long dbBlockHeight = blockService.getDbBlockHeight();
        if (nowChainHigh > dbBlockHeight) {
            flushNewBlockToDb(nowChainHigh, dbBlockHeight);
        }
    }

    public void flushNewBlockToDb(Long nowBlockHeight, Long dbBlockHeight) {
        for (Long blockHeight =  dbBlockHeight; blockHeight < nowBlockHeight; blockHeight++) {
            addDataService.addBlock(blockHeight);
        }
    }
}
