package com.horizon.net.packet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PacketFactory {

    @Autowired
    private List<PacketHandler> packets;

    private static final Map<Integer, PacketHandler> packetCache = new HashMap<>();

    @PostConstruct
    public void init() {
        for(PacketHandler packet : packets) {
            PacketEvent annotation = packet.getClass().getAnnotation(PacketEvent.class);
            if(annotation == null) {
                continue;
            }

            packetCache.put(annotation.type().getPacketId(), packet);
        }
    }

    public static PacketHandler getService(Packet packet) {
        return packetCache.get(packet.getPacketId());
    }

}
