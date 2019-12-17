package com.enterprise.entity;

import com.enterprise.project.entity.Project;
import com.user.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

//金融公司
@Entity
public class FinanceEnterprise extends Enterprise {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;

    public FinanceEnterprise() {
    }

    public FinanceEnterprise(String enterpriseHash, String founderHash,String txId,Long upChainDate) {
        super(enterpriseHash, founderHash,txId,upChainDate);
    }
}
