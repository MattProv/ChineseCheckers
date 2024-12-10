package org.example.server;

import org.example.Board;
import org.example.GameState;
import org.example.Player;
import org.example.message.GameStateMessage;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    //SETTINGS
    private int playerCount = 2;

    //RUNTIME
    private GameState gameState = new GameState();
    private List<Player> players = new ArrayList<Player>();

    public boolean startGame(List<ServerConnection> users) {
        if (gameState.isRunning()) {
            return false;
        }

        if (users.size() != playerCount) {
            return false;
        }

        if (gameState.getBoard() == null) {
            return false;
        }

        gameState.getBoard().generateBoard();
        gameState.setRunning(true);

        synchronizeBoard();

        System.out.println("Starting game.");
        return true;
    }

    public boolean setBoard(Board board) {
        if(gameState.isRunning())
            return false;
        gameState.setBoard(board);
        return true;
    }

    public boolean setPlayerCount(int playerCount) {
        if(gameState.isRunning())
            return false;
        this.playerCount = playerCount;
        return true;
    }

    public boolean makeMove(Player player, String start, String end)
    {
        if(!gameState.isRunning())
            return false;
        //if(currentPlayer != player)
        //    return false;
        gameState.getBoard().move(start, end);
        synchronizeBoard();
        return true;
    }

    public Player getPlayerByConnection(ServerConnection sc)
    {
        for(Player player : players)
        {
            if(player.getOwner() == sc)
                return player;
        }
        return null;
    }

    public void synchronizeBoard()
    {
        System.out.println("Synchronizing board.");
        GameStateMessage gsm = new GameStateMessage(gameState.clone());
        gsm.getGameState().getBoard().showBoard();
        Server.getServer().Broadcast(gsm);
    }
}