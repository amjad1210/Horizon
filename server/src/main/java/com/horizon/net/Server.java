package com.horizon.net;

import com.horizon.dao.NetworkRepository;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Amjad
 */
@Service
public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private final Map<Integer, ChannelFuture> portBinds = new ConcurrentHashMap<>();

    @Autowired
    @Qualifier("bossGroup")
    private EventLoopGroup bossGroup;

    @Autowired
    @Qualifier("workerGroup")
    private EventLoopGroup workerGroup;

    @Autowired
    private ServerBootstrap bootstrap;

    @Autowired
    private NetworkRepository networkRepository;

    @PostConstruct
    public void init() {
        networkRepository.findAll().stream().filter(p -> p.isActive()).forEach(p -> bindPort(p.getPort()));
    }

    public ChannelFuture bindPort(int port) {
        ChannelFuture channelFuture = bootstrap.bind(port).awaitUninterruptibly();

        channelFuture.addListener(cf -> {
            if(cf.isSuccess()) {
                portBinds.put(port, channelFuture);
                LOGGER.info("Listening on port " + port);
            }
        });

        return channelFuture;
    }

    public ChannelFuture unbindPort(int port) {
        ChannelFuture channelFuture = portBinds.get(port);
        if(channelFuture == null) {
            return null;
        }

        channelFuture.channel().close().awaitUninterruptibly();

        channelFuture.addListener(cf -> {
            if(cf.isSuccess()) {
                portBinds.remove(port);
                LOGGER.info("Stopped listening on port " + port);
            }
        });

        return channelFuture;
    }

    @PreDestroy
    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

        //TODO: Reset all nodes online to false
    }

}
