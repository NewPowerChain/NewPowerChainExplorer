package com.supernode.service;

import com.block.entity.Block;
import com.block.service.BlockService;
import com.msg.ReqSuperNodeTxListMsg;
import com.msg.RespBlockListMsg;
import com.msg.RespSuperNodeDetailMsg;
import com.msg.RespSuperNodeListMsg;
import com.supernode.entity.SuperNode;
import com.supernode.repository.SuperNodeRepository;
import com.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class SuperNodeService {

    @Autowired
    SuperNodeRepository superNodeRepository;
    @Autowired
    BlockService blockService;

    public List<RespSuperNodeListMsg> getSuperNodeList() {
        List<SuperNode> all = superNodeRepository.findAll();
        return all.stream().map(RespSuperNodeListMsg::convert).collect(Collectors.toList());
    }

    public RespSuperNodeDetailMsg getSuperNodeDetail(Long id) {
        SuperNode superNode = superNodeRepository.findById(id).get();
        return RespSuperNodeDetailMsg.convert(superNode);
    }

    public RespSuperNodeDetailMsg getSuperNodeDetailByName(String superNodeName) {
        SuperNode superNode = superNodeRepository.findByName(superNodeName);
        return RespSuperNodeDetailMsg.convert(superNode);
    }
}
