package com.user.service;

import com.block.tx.entity.OperationType;
import com.block.tx.entity.Tx;
import com.msg.IsUpdate;
import com.user.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(String tel, String userHash, Tx tx) {
        User user = new User(userHash,tel);
        tx.updateTxInfo(OperationType.CREATE_USER, userHash, null);
        tx.setIsUpdate(IsUpdate.TRUE);
        tx.setCallDataHash(userHash);
        return userRepository.save(user);
    }

    public Long getRegisterUserCount(){
        return userRepository.count();
    }

    public User findByUserHash(String founderHash) {
        return userRepository.findByUserHash(founderHash);
    }
}
