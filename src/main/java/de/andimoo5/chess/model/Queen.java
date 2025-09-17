package de.andimoo5.chess.model;

import java.util.List;

public class Queen extends Piece{
    public Queen(boolean isWhite, Position pos) {
        super(isWhite, pos);
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        return getLinearMoves(board, new int[][]{
                {1, 1}, {-1, 1}, {-1, 1}, {-1, -1},
                {0, 1}, {0, 1}, {0, 1}, {0, -1}
        });
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
