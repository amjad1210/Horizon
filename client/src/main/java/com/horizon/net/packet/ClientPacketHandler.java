package com.horizon.net.packet;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

@Component
@PacketEvent(type = PacketType.CLIENT_INIT)
public class ClientPacketHandler implements PacketHandler {

    @Override
    public void handlePacket(ChannelHandlerContext ctx, Packet packet) {
        System.out.println(packet.getPacketId());
    }

}
