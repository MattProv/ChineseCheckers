package org.example.message;

public abstract class MessageHandler
{
    private MessageType messageType;

    public MessageHandler(final MessageType messageType)
    {
        this.messageType = messageType;
    }

    public MessageType getMessageType()
    {
        return messageType;
    }

    public abstract void handle(MessageSenderPair message);
}