package com.service;

import com.entity.Company;
import com.msg.RespCompanyInfoMsg;
import com.repository.CompanyRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CompanyService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    public RespCompanyInfoMsg getCompanyInfo(String companyHash) {
        Company company = companyRepository.findByCompanyHash(companyHash);
        return RespCompanyInfoMsg.convert(company);
    }
}
