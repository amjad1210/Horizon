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
@PacketEvent(type = PacketType.CLIENT_DISCONNECT)
public class ClientDisconnectPacketHandler implements PacketHandler {

    @Autowired
    @Qualifier("clients")
    private Map<String, String> clients;

    @Autowired
    private NodeRepository clientRepository;

    @Override
    public void handlePacket(ChannelHandlerContext ctx, Packet packet) {
        String id = clients.get(ctx.channel().id().asShortText());
        if(id == null) {
            return;
        }

        Node node = buildModel(id);
        if(node == null) {
            return;
        }

        clients.remove(id);

        clientRepository.save(node);
    }

    @MessageMapping("/websocket")
    @SendTo("/client/disconnect")
    private Node buildModel(String id) {
        Node model = clientRepository.findOne(id);
        if(model == null) {
            return null;
        }

        model.setOnline(false);
        return model;
    }

}
