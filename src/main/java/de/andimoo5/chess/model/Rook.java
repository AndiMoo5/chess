package de.andimoo5.chess.model;

import java.util.List;

public class Rook extends Piece{
    public Rook(boolean isWhite, Position pos) {
        super(isWhite, pos);
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        return getLinearMoves(board, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}});
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
