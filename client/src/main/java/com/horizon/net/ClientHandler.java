package com.horizon.net;

import com.horizon.net.packet.Packet;
import com.horizon.net.packet.PacketBuilder;
import com.horizon.net.packet.PacketFactory;
import com.horizon.net.packet.PacketHandler;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Sharable
@Component
public class ClientHandler extends SimpleChannelInboundHandler<Packet> {

    private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    @Autowired
    private PacketBuilder packetBuilder;

    @Autowired
    private Client client;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        /**
         * Send the init packet to setup client.
         */
        ctx.writeAndFlush(packetBuilder.getClientInitPacket());

        /**
         * Send client update every second.
         */
        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.scheduleAtFixedRate(() -> {
            if (!ctx.channel().isOpen()) {
                throw new ChannelException();
            }
            ctx.writeAndFlush(packetBuilder.getClientUpdatePacket());
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet packet) {
        PacketHandler packetHandler = PacketFactory.getService(packet);
        if(packetHandler == null) {
            return;
        }

        packetHandler.handlePacket(ctx, packet);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> client.connect(), 1, TimeUnit.SECONDS);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(cause.getMessage(), cause);
    }

}
