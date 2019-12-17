package com.repository;

import com.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province,Long> {
    Province findByCode(String province);
}
