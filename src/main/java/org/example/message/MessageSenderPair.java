package org.example.message;

import org.example.server.ServerConnection;

public class MessageSenderPair {
    Message message;
    ServerConnection connection;

    public MessageSenderPair(Message message, ServerConnection connection) {
        this.message = message;
        this.connection = connection;
    }

    public Message getMessage() {
        return message;
    }

    public ServerConnection getConnection() {
        return connection;
    }

    public MessageType getMessageType() {
        return message.getType();
    }
}
