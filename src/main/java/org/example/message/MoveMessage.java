package org.example.message;

public class MoveMessage extends Message {
    String[] message; // the message content

    public MoveMessage(String start, String end)
    {
        super(MessageType.MOVE);
        this.message = new String[]{start, end};
    }


    public String[] getMessage()
    {
        return this.message;
    }
}
