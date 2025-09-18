package de.andimoo5.chess.model;

import de.andimoo5.chess.controller.GameMode;

import java.util.List;

public class GameSave {
    public List<MoveDTO> moves;
    public boolean whiteToMove;
    public GameMode gameMode;
    public long whiteTimeRemaining;
    public long blackTimeRemaining;
    public String playerWhiteName;
    public String playerBlackName;
    public Theme theme;
    public boolean showLegalMoves;


    public GameSave() {}

    public GameSave(List<MoveDTO> moves, boolean whiteToMove, GameMode mode, long whiteTimeRemaining,
                    long blackTimeRemaining, String playerWhiteName, String playerBlackName, Theme theme,
                    boolean showLegalMoves) {
        this.moves = moves;
        this.whiteToMove = whiteToMove;
        this.gameMode = mode;
        this.whiteTimeRemaining = whiteTimeRemaining;
        this.blackTimeRemaining = blackTimeRemaining;
        this.playerWhiteName = playerWhiteName;
        this.playerBlackName = playerBlackName;
        this.showLegalMoves = showLegalMoves;
    }
}
