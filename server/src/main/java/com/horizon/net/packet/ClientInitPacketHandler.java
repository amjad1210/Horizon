package com.horizon.net.packet;

import com.horizon.dao.NodeRepository;
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
@PacketEvent(type = PacketType.CLIENT_INIT)
public class ClientInitPacketHandler implements PacketHandler {

    @Autowired
    @Qualifier("clients")
    private Map<String, String> clients;

    @Autowired
    private NodeRepository clientRepository;

    @Override
    public void handlePacket(ChannelHandlerContext ctx, Packet packet) {
        Node node = buildModel(packet);

        /**
         * Save the channel uid for the node.
         */
        String id = ctx.channel().id().asShortText();
        clients.put(id, node.getId());

        clientRepository.save(node);
    }

    @MessageMapping("/websocket")
    @SendTo("/client/init")
    private Node buildModel(Packet packet) {
        Node model = new Node(packet.readString());

        model.setOnline(true);
        model.setLan(packet.readString());
        model.setHostname(packet.readString());
        model.setUser(packet.readString());
        model.setOs(packet.readString());
        return model;
    }

}
