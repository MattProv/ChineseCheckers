package org.example.message.clientHandlers;

import org.example.message.MessageHandler;
import org.example.message.MessageSenderPair;
import org.example.message.MessageType;
import org.example.message.MoveMessage;
import org.example.server.ServerConnection;

import java.util.Arrays;

public class MoveMessageHandler extends MessageHandler {
    public MoveMessageHandler() {
        super(MessageType.MOVE);
    }

    @Override
    public void handle(MessageSenderPair message) {
        MoveMessage moveMessage = (MoveMessage) message.getMessage();
        ServerConnection sc = message.getConnection();

        System.out.println(Arrays.toString(moveMessage.getMessage()));
    }
}
