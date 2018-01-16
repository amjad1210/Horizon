package com.horizon.controller;

import com.horizon.controller.type.ApiVersion;
import com.horizon.model.Node;
import com.horizon.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Amjad
 */
@ApiVersion
@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @GetMapping("/all")
    public List<Node> getNodes() {
        return nodeService.findAll();
    }

}