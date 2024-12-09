package org.example.message;

import org.example.server.Server;
import org.example.server.ServerConnection;

public class EchoMessageHandler extends MessageHandler {
    public EchoMessageHandler() {
        super(MessageType.STRING);
    }

    @Override
    public void handle(MessageSenderPair message) {
        StringMessage stringMessage = (StringMessage) message.getMessage();
        ServerConnection sc = message.getConnection();

        Server.getServer().Send(stringMessage, sc);
    }
}
