package com.repository;

import com.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component,Long> {

    List<Component> findByProjectCode(String projectCode);
}
