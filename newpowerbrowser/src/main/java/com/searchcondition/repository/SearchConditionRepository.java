package com.searchcondition.repository;

import com.searchcondition.entity.SearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchConditionRepository extends JpaRepository<SearchCondition,Long> {
    SearchCondition findByContent(String searchCondition);
}
