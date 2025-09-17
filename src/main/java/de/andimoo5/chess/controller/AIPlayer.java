package de.andimoo5.chess.controller;

import de.andimoo5.chess.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer {
    private final Random random = new Random();

    public Move calculateMove(Board board) {
        List<Move> legalMoves = getAllLegalMoves(board, false);
        if (legalMoves.isEmpty()) return null;
        return legalMoves.get(random.nextInt(legalMoves.size()));
    }

    private List<Move> getAllLegalMoves(Board board, boolean forWhite) {
        List<Move> moves = new ArrayList<>();
        for (int rank = 1; rank <= 8; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                Position from = new Position(file, rank);
                Piece piece = board.getPieceAt(from);
                if (piece != null && piece.isWhite() == forWhite) {
                    for (Position to : piece.getLegalMoves(board)) {
                        Move move = new Move(from, to, piece, board.getPieceAt(to), false, null,
                                false, false, board.getMoveHistory().size() + 1);
                        Board clone = board.cloneForSim();
                        clone.makeMove(move);
                        Position kingPos = findKing(clone, forWhite);
                        if (!clone.isSquareAttacked(kingPos, !forWhite)) {
                            moves.add(move);
                        }
                    }
                }
            }
        }
        return moves;
    }

    private Position findKing(Board board, boolean white) {
        for (int rank = 1; rank <= 8; rank++) {
            for (char file = 'a'; file <= 'h'; file ++) {
                Position pos = new Position(file, rank);
                Piece piece = board.getPieceAt(pos);
                if (piece instanceof King && piece.isWhite() == white) {
                    return pos;
                }
            }
        }
        throw new IllegalStateException("King not found.");
    }
}
