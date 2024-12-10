package org.example.message;

import org.example.server.ServerConnection;

public class LogMessageHandler extends MessageHandler {
    public LogMessageHandler(MessageType type) {
        super(type);
    }

    @Override
    public void handle(MessageSenderPair message) {
        Message msg = message.getMessage();
        ServerConnection sc = message.getConnection();

        System.out.println((sc!=null?sc.toString() + ": ":"") + "[" + msg.getType().name() +"] " + msg.toString());
    }
}
