package org.example.message.serverHandlers;

import org.example.message.CommandMessage;
import org.example.message.MessageHandler;
import org.example.message.MessageSenderPair;
import org.example.message.MessageType;
import org.example.server.Server;
import org.example.server.ServerConnection;

import java.util.Arrays;

public class CommandMessageHandler extends MessageHandler {
    public CommandMessageHandler() {super(MessageType.COMMAND);}

    @Override
    public void handle(MessageSenderPair message) {
        CommandMessage commandMessage = (CommandMessage) message.getMessage();
        ServerConnection sc = message.getConnection();

        System.out.println(Arrays.toString(commandMessage.getMessage()));
        Server.getServer().Broadcast(commandMessage);
    }
}
