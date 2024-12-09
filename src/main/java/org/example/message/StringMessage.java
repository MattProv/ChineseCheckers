package org.example.message;

public class StringMessage extends Message {
    String message; // the message content

    StringMessage(String message)
    {
        super(MessageType.STRING);
        this.message = message;
    }

}