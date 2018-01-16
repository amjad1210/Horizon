package com.horizon.dao;

import com.horizon.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Amjad
 */
@Repository
public interface NetworkRepository extends JpaRepository<Port, Integer> {
}
