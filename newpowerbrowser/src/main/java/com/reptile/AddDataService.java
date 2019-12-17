package com.reptile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.block.entity.Block;
import com.block.service.BlockService;
import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.block.tx.service.TxService;
import com.enterprise.project.contract.service.ContractService;
import com.enterprise.project.generationcapacity.service.DailyPowerGenerationService;
import com.enterprise.project.service.ProjectService;
import com.enterprise.service.EnterpriseService;
import com.msg.IsUpdate;
import com.searchcondition.entity.ConditionType;
import com.searchcondition.service.HomePageService;
import com.supernode.repository.SuperNodeRepository;
import com.user.service.UserService;
import com.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
//@Import(FeignClientsConfiguration.class)
public class AddDataService {

    @Autowired
    BlockService blockService;
    @Autowired
    TxService txService;
    @Autowired
    BlockChainFeignClient blockChainFeignClient;
    @Autowired
    HomePageService homePageService;
    @Autowired
    SuperNodeRepository superNodeRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void addBlock(Long blockHeight) {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put("block_height", blockHeight.toString());
        //获取这个block,取出txids
        JSONObject blockJSON = blockChainFeignClient.getBlock(requestParam);
        Block block = blockService.addBlock(blockJSON.getString("block_hash"), blockJSON.getString("block_height"), blockJSON.getString("previous_block_hash"), blockJSON.getInteger("size_of_block"), blockJSON.getInteger("size_of_block_header"), blockJSON.getLong("timestamp"));
        homePageService.createSearchCondition(block.getBlockHeight(), ConditionType.BLOCK);

        List<Tx> txs = addAndGetBlockTxs(blockJSON);
        if (txs != null && txs.size() != 0) {
            block.setTxs(txs);
            for (Tx tx : txs) {
                if (IsUpdate.alreadyUpdate(tx.getIsUpdate())) {
                    block.addTxSize();
                }
            }
        }
    }

    public List<Tx> addAndGetBlockTxs(JSONObject blockJSON) {
        List<String> txIds = (List<String>) blockJSON.get("tx_ids");
        List<Tx> txList = new ArrayList<>();
        for (String txId : txIds) {
            if (txId.equals("")) {
                return null;
            }
            Map<String, String> requestParam = new HashMap<>();
            requestParam.put("tx_id", txId);

            JSONObject txJSON = blockChainFeignClient.getTx(requestParam);
            JSONObject txArgsJSON = blockChainFeignClient.getTxArgs(requestParam);

            JSONArray txArgs = txArgsJSON.getJSONArray("tx_args");

            //是业务操作再添加tx
            if (OperationType.isBusinessOperation((String) txArgs.get(0))) {
                Tx tx = txService.addTx(txJSON.getLong("block_number"), txJSON.getInteger("size_of_tx"), txJSON.getLong("timestamp"), txJSON.getString("tx_id"));
                homePageService.createSearchCondition(tx.getTxId(), ConditionType.TX);
                updateTx((String) txArgs.get(0), txArgs, tx);
                txList.add(tx);
            }
        }
        return txList;
    }

    @Autowired
    UserService userService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ContractService contractService;
    @Autowired
    DailyPowerGenerationService dailyPowerGenerationService;

    public void updateTx(String operationType, JSONArray tx_args, Tx tx) {
        switch (operationType) {
            case "createUser":
/*            {
                "tx_args": [
                "createUser",  方法名
                "18513331333",  电话号码
                "0xda698A3FF558eA9C19f717AE04d0381fdcf64C4d"  用户地址
	],
                "tx_id": "7cf940a3864062f67fe333ce2f55ba4efa3af66da75dde4825c069227c016b16"
            }*/
                userService.createUser(tx_args.getString(1), tx_args.getString(2),tx);
                break;
            case "createEnterprise":
/*            {
                "tx_args": [
                "createEnterprise",  方法名
                "asset_side",  企业类型
                "8aa7f247f2b5df516c42fa6795b13ab39cb2b3df136994b71b22fa928ba6ac6a",  存证的文件hash
                "enterprise_example_file1.zip",  存证的文件name
                "‭10485760‬", 存证的文件size‬
                "[\"\"]",  存证的文件切片的地址，暂时为空
                "0xda698A3FF558eA9C19f717AE04d0381fdcf64C4d",   创建该企业的用户地址
                "0xD64df5B686383109219A093b18053b74fb7e0552"     该企业的地址
	],
                "tx_id": "6381600b4dc4ec0541b2daa49099237db3a3c646f01fe01f4bb5b9f4754fdb21"
            }*/
                enterpriseService.createEnterprise(tx_args.getString(1), tx_args.getString(2), tx_args.getString(3), tx_args.getString(4), tx_args.getString(6), tx_args.getString(7), tx);
                break;
            case "updateEnterprise":
/*                "tx_args": [
                "updateEnterprise",  方法名
                "0x59eba56F9d6A21D96cfdddf85A0Da2a99e8c7Fae",  该企业的地址
                "70d94a5f50ddbfe587fa180956b0090400a684551e46df8b79d250969ada087e",   存证的文件hash
                "你好2.zip",  存证的文件name
                "10485760‬",  存证的文件size
                "[\"\"]",  存证的文件切片的地址，暂时为空
                "0xadBFA6a94F476FF3A1368aB96e1bd0516e0Ce193"  该企业的历史存证地址
    ],
                "tx_id": "7faa9092c947d4a3820e764bedb2201aa5738b53206c0a31752f4f1a505e3a50"
        }*/
                enterpriseService.updateEnterprise(tx_args.getString(1), tx_args.getString(2), tx_args.getString(3), tx_args.getString(4), tx);
                break;
            case "createAsset":
/*            {
                "tx_args": [
                "createAsset",  方法名
                "604800",  该资产的展示时间，不用管
                "70d94a5f50ddbfe587fa180956b0090400a684551e46df8b79d250969ada087e",  存证文件的hash
                "asset_example_file_1.zip",  存证文件的name
                "10485760‬",  存证文件的size
                "[\"\"]",  存证的文件切片的地址，暂时为空
                "0xD64df5B686383109219A093b18053b74fb7e0552",   所属企业的地址
                "0xb97afD0cd7A517Fe989C93ff668E7B9743166B48"   该资产的地址
    ],
                "tx_id": "68d48ae35f86a0315c04dba0aef2a75614dbf2e605ef4dce0867264fab31e044"
            }*/
                projectService.createAsset(tx_args.getString(2), tx_args.getString(3), tx_args.getString(4), tx_args.getString(6), tx_args.getString(7), tx);
                break;
            case "updateAsset":
/*            {
                "tx_args": [
                "updateAsset",  方法名
                "604800", 该资产的展示时间，不用管
                "5bb9cd801ae475cdb1f08fc2742a4f3f6127280ddea89a5dd2dcb192fc8afcb4",  存证文件的hash

                "asset_example_file_2.zip",  存证文件的name
                "10485760‬", 存证文件的size
                "[\"\"]",  存证的文件切片的地址，暂时为空
                "0xC30BeB13b566e42cBB3C1e5510a2D8401aA5F2d6",  资产的地址
                "0x29842e92882A945e1Ad8252c4644a1EFA0c715D3"   该资产的历史存证地址

    ],
                "tx_id": "b10d9524d2814b8761406e4033e57a693886dc385c557c5dad3c74ad1721cd7d"
            }*/
                projectService.updateAsset(tx_args.getString(2), tx_args.getString(3), tx_args.getString(4), tx_args.getString(6), tx);
                break;
            case "createContract":
/*            {
                "tx_args": [
                "createContract", 方法名
                "data",  合约的类型 data or business
                "0xD64df5B686383109219A093b18053b74fb7e0552",  合约的创建者 是一个企业地址
                "0x6E28D9B804407092E7a1A0b93d3057beAD666d5E",  合约的接收者 是一个企业地址
                "604800",  合约的持续时间，不用管
                "0xC30BeB13b566e42cBB3C1e5510a2D8401aA5F2d6",  合约所属的资产地址
                "0x71328694dc6a84edDd62fC3625277c86bC9E0770"  合约的地址
    ],
                "tx_id": "85ff711b337e5e6d22a286af568b51c6f43d0af2288e8e80ad2b705c781875c1"
            }*/
                contractService.createContract(tx_args.getString(1), tx_args.getString(2), tx_args.getString(3), tx_args.getString(5), tx_args.getString(6), tx);
                break;
            case "executeContract":
/*
            {
                "tx_args": [
                "executeContract",  方法名
                "0x6E28D9B804407092E7a1A0b93d3057beAD666d5E",  合约动作的施加者
                "cancel",  合约动作 cancel execute refuse
                "0x74F2d75f2825006211c63B9238C83f7f3bf74D04",  合约地址
                "0x0071eF9B97C6763fB22192DC41715A03C598B35D"   合约的历史记录地址
    ],
                "tx_id": "2cd235b1b4294a772f47c60b31aba146600709a615495d42cd607d7eba607981"
            }*/
                contractService.executeContract(tx_args.getString(1), tx_args.getString(2), tx_args.getString(3), tx);
                break;
            case "dailyPowerGeneration":
/*            {
                "tx_args": [
                "dailyPowerGeneration",
                        "0x6A5409C871732E5230592946dD4352a308e258Fa20191010DailyPowerGeneration",
                        "18769.57"
],
                "tx_id": "4e15205df7026faea2482416f016bfd92b8c58a0fb4627bd70f3c8fe907f793d"
            }*/
                dailyPowerGenerationService.updateDailyPowerGeneration(tx_args.getString(1).substring(0,42), TimeUtils.strToDate(tx_args.getString(1).substring(42,50)),tx_args.getBigDecimal(2),tx);
                break;
            case "deploy":
            case "newBlock":
                //系统级调用,和业务无关
//            {
//                "tx_args": [
//                "deploy",
//                        "mychannel","\n-\b\n\u000b/opt/gopath\bnewpower1.0.0\t\ninit\n ","\b\b\b&#00;\u000b\t\n org0MSP",
//                        "escc",
//                        "vscc"
//    ],
//                "tx_id": "dc1c5c86f74e2e6e2e939200c2b2dbbb49c4cce2aa10c979b482b2a102b7b4ed"
//            }
                tx.updateTxInfo(OperationType.SYSTEM_DEPLOY,"NPC System",null);
                tx.setIsUpdate(IsUpdate.TRUE);
                tx.setCallDataHash(tx.getTxId());
                break;
            default:
                //doNothing
                break;
        }
    }
}