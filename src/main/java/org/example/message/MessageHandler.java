package org.example.message;

public abstract class MessageHandler
{
    protected MessageType messageType;

    public MessageHandler(MessageType messageType)
    {
        this.messageType = messageType;
    }

    public MessageType getMessageType()
    {
        return messageType;
    }

    public abstract void handle(MessageSenderPair message);
}