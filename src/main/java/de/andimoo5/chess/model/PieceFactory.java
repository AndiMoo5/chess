package de.andimoo5.chess.model;

public class PieceFactory {
    public static Piece create(PieceType type, boolean isWhite, Position pos) {
        Piece piece;
        switch (type) {
            case ROOK -> piece = new Rook(isWhite, pos);
            case KNIGHT -> piece = new Knight(isWhite, pos);
            case BISHOP -> piece = new Bishop(isWhite, pos);
            case QUEEN -> piece = new Queen(isWhite, pos);
            case KING -> piece = new King(isWhite, pos);
            default -> piece = new Pawn(isWhite, pos);
        }
        return piece;
    }

    public static Piece createClone(Piece original) {
        Piece clone = create(original.getType(), original.isWhite(), original.getPosition());
        clone.setHasMoved(original.hasMoved());
        return clone;
    }
}
