package org.example.server;

import org.example.message.Message;

public class ServerCallbacksHandler {

    public void onNewConnection(ServerConnection connection) {
        System.out.println("User connected: " + connection);
    }

    public void onConnectionClosed(ServerConnection connection) {
        System.out.println("User disconnected: " + connection);
    }

    public void onMessageReceived(ServerConnection connection, Message message) {
        System.out.println("Message received: [" + connection + "] " + message.getType().name() + " " + message);
        synchronized ( Server.getServer()) {
            Server.getServer().HandleMessages();
        }
    }

    public void onMessageSent(ServerConnection connection, Message message) {
        System.out.println("Message sent: [" + connection + "] " + message.getType().name() + " " + message);
    }
}
