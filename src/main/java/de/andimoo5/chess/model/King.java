package de.andimoo5.chess.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(boolean isWhite, Position pos) {
        super(isWhite, pos);
    }

    @Override
    public List<Position> getLegalMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy ++) {
                if (dx == 0 && dy == 0) continue;
                Position target = new Position((char) (position.file() + dx), position.rank() + dy);
                if (isValidTarget(board, target) && !board.isSquareAttacked(target, !isWhite)) {
                    moves.add(target);
                }
            }
        }
        if (!hasMoved &&
                board.isEmpty(new Position('f', position.rank())) &&
                board.isEmpty(new Position('g', position.rank()))) {
            Piece rook = board.getPieceAt(new Position('h', position.rank()));
            if (rook instanceof Rook && !rook.hasMoved() && rook.isWhite() == isWhite) {
                Position e = position;
                Position f = new Position('f', position.rank());
                Position g = new Position('g', position.rank());
                if (!board.isSquareAttacked(e, !isWhite) &&
                        !board.isSquareAttacked(f, !isWhite) &&
                        !board.isSquareAttacked(g, !isWhite)) {
                    moves.add(g);
                }
            }
        }
        if (!hasMoved &&
                board.isEmpty(new Position('c', position.rank())) &&
                board.isEmpty(new Position('d', position.rank()))) {
            Piece rook = board.getPieceAt(new Position('a', position.rank()));
            if (rook instanceof Rook && !rook.hasMoved() && rook.isWhite() == isWhite) {
                Position e = position;
                Position d = new Position('d', position.rank());
                Position c = new Position('c', position.rank());
                if (!board.isSquareAttacked(e, !isWhite) &&
                        !board.isSquareAttacked(d, !isWhite) &&
                        !board.isSquareAttacked(c, !isWhite)) {
                    moves.add(c);
                }
            }
        }
        return moves;
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
