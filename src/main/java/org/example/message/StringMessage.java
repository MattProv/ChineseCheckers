package org.example.message;

public class StringMessage extends Message {
    String message; // the message content

    public StringMessage(String message)
    {
        super(MessageType.STRING);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String toString()
    {
        return message;
    }
}