package com.supernode.repository;

import com.supernode.entity.SuperNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperNodeRepository extends JpaRepository<SuperNode,Long> {
    SuperNode findByName(String superNodeName);
}
