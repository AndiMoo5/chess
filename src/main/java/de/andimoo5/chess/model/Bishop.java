package de.andimoo5.chess.model;

import java.util.List;

public class Bishop extends Piece{
    public Bishop(boolean isWhite, Position pos) {
        super(isWhite, pos);
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        return getLinearMoves(board, new int[][]{{1, 1}, {-1, 1}, {-1, 1}, {-1, -1}});
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    @Override
    public Piece clone() {
        Piece copy = new Bishop(isWhite, position);
        copy.setHasMoved(hasMoved);
        return copy;
    }
}
