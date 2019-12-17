package com.evidence.repository;

import com.evidence.entity.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvidenceRepository extends JpaRepository<Evidence,Long> {
}
