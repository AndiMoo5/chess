package de.andimoo5.chess.model;

import de.andimoo5.chess.controller.GameMode;

import java.util.List;

public class GameSave {
    public List<MoveDTO> moves;
    public boolean whiteToMove;
    public GameMode gameMode;

    public GameSave() {}

    public GameSave(List<MoveDTO> moves, boolean whiteToMove, GameMode mode) {
        this.moves = moves;
        this.whiteToMove = whiteToMove;
        this.gameMode = mode;
    }
}
