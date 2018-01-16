package com.horizon.config;

import com.horizon.net.ServerHandler;
import com.horizon.net.packet.codec.PacketDecoder;
import com.horizon.net.packet.codec.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Amjad
 */
@Configuration
public class ServerConfig {

    @Autowired
    @Qualifier("bossGroup")
    private EventLoopGroup bossGroup;

    @Autowired
    @Qualifier("workerGroup")
    private EventLoopGroup workerGroup;

    @Autowired
    private ServerHandler serverHandler;

    @Bean(name = "nodes")
    public Map<String, String> nodes() {
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "bossGroup")
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup(1);
    }

    @Bean(name = "workerGroup")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            public void initChannel(SocketChannel sc) throws Exception {
                ChannelPipeline pipeline = sc.pipeline();

                pipeline.addLast("decoder", new PacketDecoder());
                pipeline.addLast("encoder", new PacketEncoder());

                pipeline.addLast("handler", serverHandler);
            }

        });
        return bootstrap;
    }

}
