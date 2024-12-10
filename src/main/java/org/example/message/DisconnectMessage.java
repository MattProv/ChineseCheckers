package org.example.message;

public class DisconnectMessage extends Message{
    public DisconnectMessage(){
        super(MessageType.DISCONNECT);
    }

    public String toString(){
        return "DISCONNECT";
    }
}
