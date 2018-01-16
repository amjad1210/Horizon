package com.horizon.dao;

import com.horizon.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Amjad
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, String> {
}
