package org.example.message.clientHandlers;

import org.example.message.CommandMessage;
import org.example.message.MessageHandler;
import org.example.message.MessageSenderPair;
import org.example.message.MessageType;

import java.util.Arrays;

public class CommandMessageHandler extends MessageHandler {
    public CommandMessageHandler() {super(MessageType.COMMAND);}

    @Override
    public void handle(MessageSenderPair message) {
        CommandMessage commandMessage = (CommandMessage) message.getMessage();
        System.out.println("SERVER COMMAND: " + Arrays.toString(commandMessage.getMessage()));
    }
}
