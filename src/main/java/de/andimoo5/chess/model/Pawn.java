package de.andimoo5.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    public Pawn(boolean isWhite, Position pos) {
        super(isWhite, pos);
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int dir = isWhite ? 1 : -1;
        Position oneStep = new Position(position.file(), position.rank() + dir);
        if (board.isEmpty(oneStep)) {
            moves.add(oneStep);
            if (!hasMoved) {
                Position twoStep = new Position(position.file(), position.rank() + (2 * dir));
                if (board.isEmpty(twoStep)) moves.add(twoStep);
            }
        }
        for (int dx : new int[]{-1, 1}) {
            char targetFile = (char) (position.file() + dx);
            int targetRank = position.rank() + dir;
            Position target = new Position(targetFile, targetRank);
            if (board.isValidPosition(target)) {
                Piece p = board.getPieceAt(target);
                if (p != null && p.isOpponent(this)) moves.add(target);
                Position ep = board.getEnPassantTarget();
                if (ep != null && ep.equals(target)) moves.add(target);
            }
        }
        return moves;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
