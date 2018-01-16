package com.horizon.net.packet;

import com.horizon.model.Node;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Amjad
 */
@Component
@PacketEvent(type = PacketType.CLIENT_UPDATE)
public class ClientUpdatePacketHandler implements PacketHandler {

    @Autowired
    @Qualifier("clients")
    private Map<String, String> clients;

    @Override
    public void handlePacket(ChannelHandlerContext ctx, Packet packet) {
        String id = clients.get(ctx.channel().id().asShortText());
        if(id == null) {
            return;
        }

        buildModel(id, packet);
    }

    @MessageMapping("/websocket")
    @SendTo("/client/update")
    private Node buildModel(String id, Packet packet) {
        Node model = new Node(id);

        model.setCpu(packet.getPayload().readInt());
        model.setRamUsage(packet.readString());
        model.setRamUsagePercentage(packet.getPayload().readInt());
        model.setUptime(packet.readString());
        return model;
    }

}
