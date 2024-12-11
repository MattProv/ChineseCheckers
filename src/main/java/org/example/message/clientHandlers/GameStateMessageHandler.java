package org.example.message.clientHandlers;

import org.example.GameState;
import org.example.TestBoard;
import org.example.message.GameStateMessage;
import org.example.message.MessageHandler;
import org.example.message.MessageSenderPair;
import org.example.message.MessageType;

public class GameStateMessageHandler extends MessageHandler {

    GameState gameState;

    public GameStateMessageHandler(GameState gm) {
        super(MessageType.GAMESTATE);
        this.gameState = gm;
    }

    @Override
    public void handle(MessageSenderPair message) {
        GameStateMessage bm = (GameStateMessage) message.getMessage();
        TestBoard board = (TestBoard) bm.getGameState().getBoard();

        gameState.setState(bm.getGameState());
        gameState.getBoard().showBoard();
    }
}
