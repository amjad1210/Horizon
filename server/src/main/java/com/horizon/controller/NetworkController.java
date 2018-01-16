package com.horizon.controller;

import com.horizon.controller.type.ApiVersion;
import com.horizon.exception.RestApiException;
import com.horizon.model.Port;
import com.horizon.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Amjad
 */
@ApiVersion
@RestController
@RequestMapping("/network")
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    /**
     * Return a list of ports that have been added
     *
     * @return
     */
    @GetMapping("/all")
    public List<Port> getPorts() {
        return networkService.findAll();
    }

    @PostMapping("/add")
    public Port add(@RequestBody Port port) throws RestApiException {
        return networkService.addPort(port);
    }

    @PostMapping("/remove")
    public void remove(@RequestParam("port") int port) throws RestApiException {
        networkService.removePort(port);
    }

    @PostMapping("/bind")
    public Future<Port> bind(@RequestParam("port") int port) throws RestApiException {
        return networkService.bindPort(port);
    }

    @PostMapping("/unbind")
    public Future<Port> unbind(@RequestParam("port") int port) throws RestApiException {
        return networkService.unbindPort(port);
    }

}
