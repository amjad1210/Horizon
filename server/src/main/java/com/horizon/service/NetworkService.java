package com.horizon.service;

import com.horizon.dao.NetworkRepository;
import com.horizon.exception.RestApiException;
import com.horizon.model.Port;
import com.horizon.net.Server;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Amjad
 */
@Service
public class NetworkService {

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private Server server;

    public List<Port> findAll() {
        return networkRepository.findAll();
    }

    public Port addPort(Port port) throws RestApiException {
        if (!port.isValid()) {
            throw new RestApiException("An invalid port number was supplied");
        }

        if(networkRepository.exists(port.getPort())) {
            throw new RestApiException("Port has already been added");
        }

        return networkRepository.save(port);
    }

    public void removePort(int port) throws RestApiException {
        if(!networkRepository.exists(port)) {
            throw new RestApiException("Port doesn't exist in repository");
        }

        networkRepository.delete(port);
    }

    public Future<Port> bindPort(int port) throws RestApiException {
        ChannelFuture channelFuture = server.bindPort(port);

        CompletableFuture<Port> future = new CompletableFuture<>();

        channelFuture.addListener(cf ->
                handleChannelActive((ChannelFuture) cf, future, port, true));

        return future;
    }

    public Future<Port> unbindPort(int port) throws RestApiException {
        ChannelFuture channelFuture = server.unbindPort(port);

        if(channelFuture == null) {
            throw new RestApiException("Address doesn't exist: unbind");
        }

        CompletableFuture<Port> future = new CompletableFuture<>();

        channelFuture.addListener(cf ->
                handleChannelActive((ChannelFuture) cf, future, port, false));

        return future;
    }

    private void handleChannelActive(ChannelFuture cf, CompletableFuture<Port> future, int port, boolean active) {
        if (!cf.isSuccess()) {
            future.completeExceptionally(new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, cf.cause().getMessage()));
            return;
        }

        Port portModel = networkRepository.findOne(port);
        if(portModel == null) {
            portModel = new Port(port);
        }
        portModel.setActive(active);

        networkRepository.save(portModel);

        future.complete(portModel);
    }

}
