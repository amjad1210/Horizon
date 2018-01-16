package com.horizon.net.packet.codec;

import com.horizon.net.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Decode packets
 *
 */
public class PacketDecoder extends ByteToMessageDecoder {

    /**
     * Decoder
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();

        // Enough to read the packet id
        if (readableBytes < 4) {
            return;
        }

        ByteBuf payload = in.readBytes(readableBytes);

        out.add(new Packet(payload));
    }

}