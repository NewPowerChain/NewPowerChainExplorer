package com.repository;

import com.entity.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegalPersonRepository extends JpaRepository<LegalPerson,Long> {

    List<LegalPerson> findByCompanyId(Long companyId);
}
