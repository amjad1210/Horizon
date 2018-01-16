package com.horizon.config;

import com.horizon.net.ClientHandler;
import com.horizon.net.packet.codec.PacketDecoder;
import com.horizon.net.packet.codec.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Autowired
    private EventLoopGroup eventLoopGroup;

    @Autowired
    private ClientHandler clientHandler;

    @Bean
    public EventLoopGroup eventLoopGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public Bootstrap bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            public void initChannel(SocketChannel sc) throws Exception {
                ChannelPipeline pipeline = sc.pipeline();

                pipeline.addLast("decoder", new PacketDecoder());
                pipeline.addLast("encoder", new PacketEncoder());

                pipeline.addLast("handler", clientHandler);
            }

        });
        return bootstrap;
    }

}
