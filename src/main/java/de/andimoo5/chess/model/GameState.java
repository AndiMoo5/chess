package de.andimoo5.chess.model;

import java.util.List;

public class GameState {
    private final Board board;
    private GameStatus status;

    public GameState() {
        board = new Board();
        status = GameStatus.ONGOING;
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public boolean isWhiteToMove() {
        return board.isWhiteToMove();
    }

    public List<Move> getMoveHistory() {
        return board.getMoveHistory();
    }

    public void applyMove(Move move) {
        if (!isLegalMove(move)) {
            throw new IllegalArgumentException("Illegal move attempted: " + move);
        }
        board.makeMove(move);
        updateGameStatus();
    }

    private void updateGameStatus() {
        boolean whiteInCheck = isKingInCheck(true);
        boolean blackInCheck = isKingInCheck(false);
        boolean whiteHasMoves = hasLegalMoves(true);
        boolean blackHasMoves = hasLegalMoves(false);
        if (isWhiteToMove()) {
            if (whiteInCheck && !whiteHasMoves) {
                status = GameStatus.CHECKMATE;
            } else if (!whiteInCheck && !whiteHasMoves) {
                status = GameStatus.STALEMATE;
            } else if (whiteInCheck) {
                status = GameStatus.CHECK;
            } else {
                status = GameStatus.ONGOING;
            }
        } else {
            if (blackInCheck && !blackHasMoves) {
                status = GameStatus.CHECKMATE;
            } else if (!blackInCheck && !blackHasMoves) {
                status = GameStatus.STALEMATE;
            } else if (blackInCheck) {
                status = GameStatus.CHECK;
            } else {
                status = GameStatus.ONGOING;
            }
        }
    }

    private boolean isKingInCheck(boolean white) {
        for (int rank = 1; rank <= 8; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                Position pos = new Position(file, rank);
                Piece piece = board.getPieceAt(pos);
                if (piece instanceof King && piece.isWhite() == white) {
                    return board.isSquareAttacked(pos, !white);
                }
            }
        }
        return false;
    }

    private boolean hasLegalMoves(boolean forWhite) {
        for (int rank = 1; rank <= 8; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                Position pos = new Position(file, rank);
                Piece piece = board.getPieceAt(pos);
                if (piece != null && piece.isWhite() == forWhite) {
                    List<Position> targets = piece.getLegalMoves(board);
                    for (Position target : targets) {
                        Move testMove = new Move(pos, target, piece, board.getPieceAt(target), false,
                                null, false, false,
                                board.getMoveHistory().size() + 1);
                        Board clone = board.cloneForSim();
                        clone.makeMove(testMove);
                        if (!clone.isSquareAttacked(findKing(clone, forWhite), !forWhite)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Position findKing(Board board, boolean white) {
        for (int rank = 1; rank <= 8; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                Position pos = new Position(file, rank);
                Piece piece = board.getPieceAt(pos);
                if (piece instanceof King && piece.isWhite() == white) {
                    return pos;
                }
            }
        }
        throw new IllegalStateException("King not found on board.");
    }

    public boolean isLegalMove(Move move) {
        Piece piece = move.getMovedPiece();
        if (piece == null || piece.isWhite != isWhiteToMove()) return false;
        List<Position> legalTargets = piece.getLegalMoves(board);
        if (!legalTargets.contains(move.getTo())) return false;
        Board clone = board.cloneForSim();
        clone.makeMove(move);
        Position kingPos = findKing(clone, piece.isWhite());
        return !clone.isSquareAttacked(kingPos, !piece.isWhite());
    }
}
