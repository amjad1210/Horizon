package com.horizon.net;

import com.horizon.net.packet.Packet;
import com.horizon.net.packet.PacketFactory;
import com.horizon.net.packet.PacketHandler;
import com.horizon.net.packet.PacketType;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Amjad
 */
@Component
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    private static final Logger LOGGER = Logger.getLogger(ServerHandler.class.getName());

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
        PacketHandler disconnectPacketHandler = PacketFactory.getService(PacketType.CLIENT_DISCONNECT.newPacket());
        disconnectPacketHandler.handlePacket(ctx, null);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(cause.getMessage(), cause);
    }

}
