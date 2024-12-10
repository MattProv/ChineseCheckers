package org.example;

import java.io.Serializable;

public class GameState implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private Board board;
    private boolean isRunning;

    public GameState(Board board, boolean isRunning) {
        this.board = board;
        this.isRunning = isRunning;
    }

    public GameState() {
        this.board = null;
        this.isRunning = false;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setState(GameState state) {
        this.isRunning = state.isRunning;
        this.board = state.board;
    }

    public void setRunning(boolean b) {
        this.isRunning = b;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public GameState clone() {
        try {
            GameState cloned = (GameState) super.clone();
            cloned.board = (TestBoard) this.board.clone(); // Klonujemy Board
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
