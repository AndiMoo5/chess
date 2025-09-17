package de.andimoo5.chess.model;

public enum PieceType {
    PAWN("♙", "Pawn"),
    ROOK("♖", "Rook"),
    KNIGHT("♘", "Knight"),
    BISHOP("♗", "Bishop"),
    QUEEN("♕", "Queen"),
    KING("♔", "King");

    private final String symbol;
    private final String displayName;

    PieceType(String symbol, String displayName) {
        this.symbol = symbol;
        this.displayName = displayName;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
