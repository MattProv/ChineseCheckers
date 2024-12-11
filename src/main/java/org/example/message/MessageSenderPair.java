package org.example.message;

import org.example.server.ServerConnection;

public class MessageSenderPair {
    private Message message;
    private ServerConnection connection;

    public MessageSenderPair(final Message message, final ServerConnection connection) {
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
