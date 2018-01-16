package com.horizon.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class Client {

    @Autowired
    private EventLoopGroup eventLoopGroup;

    @Autowired
    private Bootstrap bootstrap;

    @PostConstruct
    public void connect() {
        bootstrap.connect("127.0.0.1", 1210);
    }

    @PreDestroy
    public void stop() {
        eventLoopGroup.shutdownGracefully();
    }

}
