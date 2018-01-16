package com.horizon.service;

import com.horizon.dao.NodeRepository;
import com.horizon.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Amjad
 */
@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

}
