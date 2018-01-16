package com.horizon.net.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;

/**
 * Packet
 *
 */
@Getter
public class Packet {

    private final int packetId;

    private final ByteBuf payload;

    /**
     * Constructor
     *
     * @param packetId
     *            id of the packet
     */
    public Packet(int packetId) {
        this.packetId = packetId;

        payload = Unpooled.buffer(4);
        payload.writeInt(packetId);
    }

    /**
     * Create a packet from an existing byte buffer
     *
     * @param payload
     */
    public Packet(ByteBuf payload) {
        this.payload = payload;
        packetId = this.payload.readInt();
    }

    /**
     * Write a string
     */
    public void writeString(String str) {
        payload.writeInt(str.length());

        for (char c : str.toCharArray()) {
            payload.writeByte((byte) c);
        }
    }

    /**
     * Get a string
     *
     * @return
     */
    public String readString() {
        byte[] bytes = new byte[payload.readInt()];
        payload.readBytes(bytes);
        return new String(bytes);
    }

    /**
     * Convert the packet to bytes
     *
     * @return
     */
    public byte[] toBytes() {
        return payload.array();
    }

}