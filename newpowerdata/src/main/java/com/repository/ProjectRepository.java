package com.repository;

import com.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectHashAndFlagAndStatusGreaterThanEqual(String projectHash,Integer flag,Integer status);
}
