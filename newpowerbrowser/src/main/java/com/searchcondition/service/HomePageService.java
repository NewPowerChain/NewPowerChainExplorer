package com.searchcondition.service;

import com.block.entity.Block;
import com.block.service.BlockService;
import com.block.tx.entity.Tx;
import com.block.tx.entity.TxType;
import com.block.tx.service.TxService;
import com.enterprise.entity.Enterprise;
import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.google.common.collect.ImmutableMap;
import com.msg.RespCountOfWeekTxType;
import com.msg.RespSearchMsg;
import com.reptile.BlockChainFeignClient;
import com.searchcondition.entity.ConditionType;
import com.searchcondition.entity.SearchCondition;
import com.searchcondition.msg.RespCountOfDataMsg;
import com.searchcondition.repository.SearchConditionRepository;
import com.user.service.UserService;
import com.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Transactional
@Service
public class HomePageService {

    @Autowired
    UserService userService;
    @Autowired
    BlockService blockService;
    @Autowired
    TxService txService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    ProjectService projectService;
    @Autowired
    SearchConditionRepository searchConditionRepository;
    @Autowired
    EntityManager em;
    @Autowired
    ContractService contractService;
    @Autowired
    BlockChainFeignClient blockChainFeignClient;


//    private RespSearchMsg createRespSearchMsg(Block block, Tx tx, Enterprise enterprise) {
//        if (block == null && tx == null && enterprise == null) {
//            throw new RuntimeException("未查询到所需信息");
//        }
//        return new RespSearchMsg(block, tx, enterprise);
//    }

    public RespSearchMsg search(String searchCondition) throws InterruptedException {


        SearchCondition content = searchConditionRepository.findByContent(searchCondition);

//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
//        fullTextEntityManager.createIndexer().startAndWait();
//
//
//        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder().forEntity(SearchCondition.class).get();
//        org.apache.lucene.search.Query luceneQuery = qb
//                .keyword()
//                .onField("content")
//                .matching(searchCondition)
//                .createQuery();
//
//        javax.persistence.Query jpaQuery =
//                fullTextEntityManager.createFullTextQuery(luceneQuery, SearchCondition.class);
//
//        List<SearchCondition> result = jpaQuery.getResultList();


//        if (result == null || result.size() == 0) {
//            throw new RuntimeException("未查询到所需信息");
//        }
        if (content == null) {
            throw new RuntimeException("未查询到所需信息");
        }

        Block block = null;
        Tx tx = null;
        Enterprise enterprise = null;

//        for (SearchCondition condition : result) {
        switch (content.getConditionType()) {
            case BLOCK:
                block = blockService.findByBlockHeight(searchCondition);
                break;
            case TX:
                tx = txService.findByTxId(searchCondition);
                break;
            case ENTERPRISE:
                enterprise = enterpriseService.findByEnterpriseHash(searchCondition);
                break;
        }
//        }


        return new RespSearchMsg(block, tx, enterprise,userService,projectService,contractService,blockChainFeignClient,enterpriseService);
    }

    public void createSearchCondition(String searchCondition, ConditionType conditionType) {
        SearchCondition condition = new SearchCondition(searchCondition, conditionType);
        searchConditionRepository.save(condition);
    }


    public RespCountOfDataMsg getCountOfData() {
        Long userCount = userService.getRegisterUserCount();
        Long blockCount = blockService.getDbBlockHeight();
        Long txCount = txService.getNowTxCount();
        Long projectCount = projectService.getNowProjectCount();
        BigDecimal installedCapacity = projectService.getSumOfInstalledCapacity();
        return new RespCountOfDataMsg(userCount, blockCount, txCount, projectCount, installedCapacity);

    }

    public RespCountOfWeekTxType getCountOfWeekTxType() {
        Map<String, Map<String, Long>> countOfWeekTxType = new LinkedHashMap<>();
        countOfWeekTxType.put(TimeUtils.getMdDateString(-24 * 7), ImmutableMap.of("合约", txService.getOneDayTxByTxType(TxType.getDifferentTxType("合约"), TimeUtils.dayTimeInMillis() - 86400L * 7L, TimeUtils.dayTimeInMillis() - 86400L * 6L - 1L), "存证", txService.getOneDayTxByTxType(TxType.getDifferentTxType("存证"), TimeUtils.dayTimeInMillis() - 86400L * 7L, TimeUtils.dayTimeInMillis() - 86400L * 6L - 1L)));
        countOfWeekTxType.put(TimeUtils.getMdDateString(-24 * 6), ImmutableMap.of("合约", txService.getOneDayTxByTxType(TxType.getDifferentTxType("合约"), TimeUtils.dayTimeInMillis() - 86400L * 6L, TimeUtils.dayTimeInMillis() - 86400L * 5L - 1L), "存证", txService.getOneDayTxByTxType(TxType.getDifferentTxType("存证"), TimeUtils.dayTimeInMillis() - 86400L * 6L, TimeUtils.dayTimeInMillis() - 86400L * 5L - 1L)));
        countOfWeekTxType.put(TimeUtils.getMdDateString(-24 * 5), ImmutableMap.of("合约", txService.getOneDayTxByTxType(TxType.getDifferentTxType("合约"), TimeUtils.dayTimeInMillis() - 86400L * 5L, TimeUtils.dayTimeInMillis() - 86400L * 4L - 1L), "存证", txService.getOneDayTxByTxType(TxType.getDifferentTxType("存证"), TimeUtils.dayTimeInMillis() - 86400L * 5L, TimeUtils.dayTimeInMillis() - 86400L * 4L - 1L)));
        countOfWeekTxType.put(TimeUtils.getMdDateString(-24 * 4), ImmutableMap.of("合约", txService.getOneDayTxByTxType(TxType.getDifferentTxType("合约"), TimeUtils.dayTimeInMillis() - 86400L * 4L, TimeUtils.dayTimeInMillis() - 86400L * 3L - 1L), "存证", txService.getOneDayTxByTxType(TxType.getDifferentTxType("存证"), TimeUtils.dayTimeInMillis() - 86400L * 4L, TimeUtils.dayTimeInMillis() - 86400L * 3L - 1L)));
        countOfWeekTxType.put(TimeUtils.getMdDateString(-24 * 3), ImmutableMap.of("合约", txService.getOneDayTxByTxType(TxType.getDifferentTxType("合约"), TimeUtils.dayTimeInMillis() - 86400L * 3L, TimeUtils.dayTimeInMillis() - 86400L * 2L - 1L), "存证", txService.getOneDayTxByTxType(TxType.getDifferentTxType("存证"), TimeUtils.dayTimeInMillis() - 86400L * 3L, TimeUtils.dayTimeInMillis() - 86400L * 2L - 1L)));
        countOfWeekTxType.put(TimeUtils.getMdDateString(-24 * 2), ImmutableMap.of("合约", txService.getOneDayTxByTxType(TxType.getDifferentTxType("合约"), TimeUtils.dayTimeInMillis() - 86400L * 2L, TimeUtils.dayTimeInMillis() - 86400L - 1L), "存证", txService.getOneDayTxByTxType(TxType.getDifferentTxType("存证"), TimeUtils.dayTimeInMillis() - 86400L * 2L, TimeUtils.dayTimeInMillis() - 86400L - 1L)));
        countOfWeekTxType.put(TimeUtils.getMdDateString(-24), ImmutableMap.of("合约", txService.getOneDayTxByTxType(TxType.getDifferentTxType("合约"), TimeUtils.dayTimeInMillis() - 86400L, TimeUtils.dayTimeInMillis() - 1L), "存证", txService.getOneDayTxByTxType(TxType.getDifferentTxType("存证"), TimeUtils.dayTimeInMillis() - 86400L * 1L, TimeUtils.dayTimeInMillis() - 1L)));
        return new RespCountOfWeekTxType(countOfWeekTxType);
    }
}
