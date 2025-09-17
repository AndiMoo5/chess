package de.andimoo5.chess.model;

import java.util.List;

public class GameSave {
    public List<MoveDTO> moves;
    public boolean whiteToMove;
    public boolean vsAI;

    public GameSave() {}

    public GameSave(List<MoveDTO> moves, boolean whiteToMove, boolean vsAI) {
        this.moves = moves;
        this.whiteToMove = whiteToMove;
        this.vsAI = vsAI;
    }
}
