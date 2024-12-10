package org.example.message.serverHandlers;

import org.example.message.MessageHandler;
import org.example.message.MessageSenderPair;
import org.example.message.MessageType;
import org.example.message.MoveMessage;
import org.example.server.GameManager;
import org.example.server.ServerConnection;

public class MoveMessageHandler extends MessageHandler {

    GameManager gameManager;

    public MoveMessageHandler(GameManager gameManager)
    {
        super(MessageType.MOVE);
        this.gameManager = gameManager;
    }

    @Override
    public void handle(MessageSenderPair message) {
        MoveMessage moveMessage = (MoveMessage) message.getMessage();
        ServerConnection sc = message.getConnection();

        gameManager.makeMove(
                gameManager.getPlayerByConnection(sc),
                moveMessage.getStart(),
                moveMessage.getEnd()
        );
    }
}
