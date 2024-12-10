package org.example.message;

public class CommandMessage extends Message {
    private final String[] message;
    public CommandMessage(String[] message) {
        super(MessageType.COMMAND);
        this.message = message;
    }

    public String[] getMessage(){return this.message;}
}
