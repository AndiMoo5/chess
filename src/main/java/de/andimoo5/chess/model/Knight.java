package de.andimoo5.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    public Knight(boolean isWhite, Position pos) {
        super(isWhite, pos);
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int[][] offsets = {
                {1, 2}, {2,1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}
        };
        for (int[] o : offsets) {
            Position target = new Position((char)(position.file() + o[0]), position.rank() + o[1]);
            if (isValidTarget(board, target)) moves.add(target);
        }
        return moves;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    @Override
    public Piece clone() {
        Piece copy = new Knight(isWhite, position);
        copy.setHasMoved(hasMoved);
        return copy;
    }
}
