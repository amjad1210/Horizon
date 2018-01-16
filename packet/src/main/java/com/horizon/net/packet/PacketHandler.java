package com.horizon.net.packet;

import io.netty.channel.ChannelHandlerContext;

public interface PacketHandler {

    public void handlePacket(ChannelHandlerContext ctx, Packet packet);

}