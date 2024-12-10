package org.example.message;

import org.example.GameState;

public class GameStateMessage extends Message {

    GameState gameState;
    //current turn

    public GameStateMessage(GameState gameState) {
        super(MessageType.GAMESTATE);
        this.gameState = gameState;
    }

    @Override
    public String toString() {
        return "Game is " + (gameState.isRunning()?"running":"not running") + ".";
    }

    public GameState getGameState() {
        return gameState;
    }
}
