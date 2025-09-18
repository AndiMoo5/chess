package de.andimoo5.chess.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final boolean isWhite;
    protected Position position;
    protected boolean hasMoved;

    protected Piece(boolean isWhite, Position pos) {
        this.isWhite = isWhite;
        position = pos;
    }

    public abstract List<Position> getLegalMoves(Board board);

    public abstract PieceType getType();

    public boolean isWhite() {
        return isWhite;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos) {
        position = pos;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean moved) {
        hasMoved = moved;
    }

    public boolean isOpponent(Piece other) {
        return isWhite != other.isWhite;
    }

    protected boolean isValidTarget(Board board, Position pos) {
        return board.isValidPosition(pos) && (board.isEmpty(pos) || board.getPieceAt(pos).isOpponent(this));
    }

    protected List<Position> getLinearMoves(Board board, int[][] directions) {
        List<Position> moves = new ArrayList<>();
        for (int[] d : directions) {
            int f = position.getFileIndex();
            int r = position.getRankIndex();
            while (true) {
                f += d[0];
                r += d[1];
                Position target = new Position((char) ('a' + f), r + 1);
                if (!board.isValidPosition(target)) break;
                if (board.isEmpty(target)) {
                    moves.add(target);
                } else {
                    if (board.getPieceAt(target).isOpponent(this)) moves.add(target);
                    break;
                }
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return getType().toString() + " at " + position.toString();
    }

    public abstract Piece clone();
}
