package com.horizon.net.packet;

import lombok.Getter;

/**
 * Enum of packet types
 *
 */
@Getter
public enum PacketType {

    CLIENT_INIT(1),
    CLIENT_UPDATE(2),
    CLIENT_DISCONNECT(3);

    private final int packetId;

    private PacketType(int packetId) {
        this.packetId = packetId;
    }

    /**
     * Create a new packet
     *
     * @return
     */
    public Packet newPacket() {
        return new Packet(this.packetId);
    }

}