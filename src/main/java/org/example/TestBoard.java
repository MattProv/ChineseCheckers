package org.example;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TestBoard extends Board implements Serializable {
    private static final long serialVersionUID = 1L; // Opcjonalne, ale zalecane

    private ArrayList<String> moves = new ArrayList<>();
    private String lastMove = null;

    @Override
    public void generateBoard() {
        moves.clear();
    }

    @Override
    public void move(String start, String end) {
        String mv = start + " -> " + end;
        moves.add(mv);
        lastMove = mv;
        System.out.println("Move " + start + " to " + end);
    }

    @Override
    public void showBoard() {
        System.out.println("Last move: " + lastMove);
        System.out.println("MOVES:");
        for (String move : moves) {
            System.out.println(move);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("Rozpoczynamy deserializację...");
        in.defaultReadObject();

        if (moves == null) {
            moves = new ArrayList<>();
        }

        if (lastMove == null) {
            lastMove = "";
        }

        System.out.println("Deserializacja zakończona, moves: " + moves + ", lastMove: " + lastMove);
    }

    @Override
    public TestBoard clone() throws CloneNotSupportedException {
        TestBoard cloned = (TestBoard) super.clone();
        cloned.moves = new ArrayList<>(this.moves); // kopiujemy listę
        cloned.lastMove = this.lastMove; // kopiujemy ostatni ruch
        return cloned;
    }

}
