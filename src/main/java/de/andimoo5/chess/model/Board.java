package de.andimoo5.chess.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final Piece[][] grid = new Piece[8][8];
    private final List<Move> moveHistory = new ArrayList<>();
    private Position enPassantTarget;
    private boolean whiteToMove = true;

    public Board() {
        initializeBoard();
    }

    public void initializeBoard() {
        for (char file = 'a'; file <= 'h'; file++) {
            grid[Position.getFromString(file + "2").getRankIndex()]
                    [Position.getFromString(file + "2").getFileIndex()]
                    = new Pawn(true, new Position(file, 2));
            grid[Position.getFromString(file + "2").getRankIndex()]
                    [Position.getFromString(file + "2").getFileIndex()]
                    = new Pawn(false, new Position(file, 7));
        }
        placeBackRank(true, 1);
        placeBackRank(false, 8);
    }

    private void placeBackRank(boolean isWhite, int rank) {
        char[] order = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Piece[] pieces = {
                new Rook(isWhite, new Position('a', rank)),
                new Knight(isWhite, new Position('b', rank)),
                new Bishop(isWhite, new Position('c', rank)),
                new Queen(isWhite, new Position('d', rank)),
                new King(isWhite, new Position('e', rank)),
                new Bishop(isWhite, new Position('f', rank)),
                new Knight(isWhite, new Position('g', rank)),
                new Rook(isWhite, new Position('h', rank)),
        };
        for (int i = 0; i < 8; i++) {
            grid[rank - 1][order[i] - 'a'] = pieces[i];
        }
    }

    public Piece getPieceAt(Position pos) {
        return grid[pos.getRankIndex()][pos.getFileIndex()];
    }

    public void setPieceAt(Position pos, Piece piece) {
        grid[pos.getRankIndex()][pos.getFileIndex()] = piece;
        if (piece != null) {
            piece.setPosition(pos);
        }
    }

    public boolean isEmpty(Position pos) {
        return getPieceAt(pos) == null;
    }

    public boolean isValidPosition(Position pos) {
        return pos.file() >= 'a' && pos.file() <= 'h' && pos.rank() >= 1 && pos.rank() <= 8;
    }

    public void makeMove(Move move) {
        setPieceAt(move.getTo() , move.getMovedPiece());
        setPieceAt(move.getFrom(), null);
        move.getMovedPiece().setHasMoved(true);
        if (move.isEnPassant()) {
            Position capturedPos = new Position(move.getTo().file(), move.getFrom().rank());
            setPieceAt(capturedPos, null);
        }
        if (move.isPromotion()) {
            Piece promoted = PieceFactory.create(move.getPromotionType(), move.getMovedPiece().isWhite, move.getTo());
            setPieceAt(move.getTo(), promoted);
        }
        if (move.isCastling()) {
            handleCastling(move);
        }
        moveHistory.add(move);
        whiteToMove = !whiteToMove;
        updateEnPassantTarget(move);
    }

    private void handleCastling(Move move) {
        int rank = move.getFrom().rank();
        if (move.getTo().file() == 'g') {
            Position rookFrom = new Position('h', rank);
            Position rookTo = new Position('f', rank);
            Piece rook = getPieceAt(rookFrom);
            setPieceAt(rookTo, rook);
            setPieceAt(rookFrom, null);
            if (rook != null) {
                rook.setHasMoved(true);
                rook.setPosition(rookTo);
            }
        } else if (move.getTo().file() == 'c') {
            Position rookFrom = new Position('a', rank);
            Position rookTo = new Position('d', rank);
            Piece rook = getPieceAt(rookFrom);
            setPieceAt(rookTo, rook);
            setPieceAt(rookFrom, null);
            if (rook != null) {
                rook.setHasMoved(true);
                rook.setPosition(rookTo);
            }
        }
    }

    public void updateEnPassantTarget(Move move) {
        if (move.getMovedPiece().getType() == PieceType.PAWN &&
                Math.abs(move.getFrom().rank() - move.getTo().rank()) == 2) {
            enPassantTarget = new Position(move.getFrom().file(),
                    (move.getFrom().rank() + move.getTo().rank()) / 2);
        } else {
            enPassantTarget = null;
        }
    }

    public Position getEnPassantTarget() {
        return enPassantTarget;
    }

    public boolean isWhiteToMove() {
        return whiteToMove;
    }

    public List<Move> getMoveHistory() {
        return Collections.unmodifiableList(moveHistory);
    }

    public boolean isSquareAttacked(Position pos, boolean byWhite) {
        for (int rank = 1; rank <= 8; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                Position current = new Position(file, rank);
                Piece piece = getPieceAt(current);
                if (piece != null && piece.isWhite() == byWhite) {
                    List<Position> moves = piece.getLegalMoves(this);
                    if (moves.contains(pos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Board cloneForSim() {
        Board copy = new Board();
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                copy.grid[rank][file] = null;
            }
        }
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece original = this.grid[rank][file];
                if (original != null) {
                    Piece clone = PieceFactory.createClone(original);
                    copy.grid[rank][file] = clone;
                }
            }
        }
        if (this.enPassantTarget != null) {
            copy.enPassantTarget = new Position(this.enPassantTarget.file(), this.enPassantTarget.rank());
        }
        for (Move move : this.moveHistory) {
            copy.moveHistory.add(move.clone());
        }
        copy.whiteToMove = this.whiteToMove;
        return copy;
    }

    public void undoMove(Move move) {
        Piece piece = getPieceAt(move.getTo());
        if (move.isPromotion()) {
            piece = new Pawn(piece.isWhite(), move.getFrom());
        }
        setPieceAt(move.getFrom(), piece);
        piece.setHasMoved(move.getMovedPiece().hasMoved());
        setPieceAt(move.getTo(), null);
        if (move.getCapturedPiece() != null) {
            if (move.isEnPassant()) {
                Position capturedPos = new Position(move.getTo().file(), move.getFrom().rank());
                setPieceAt(capturedPos, move.getCapturedPiece());
            } else {
                setPieceAt(move.getTo(), move.getCapturedPiece());
            }
        }
        if (move.isCastling()) {
            int rank = move.getFrom().rank();
            if (move.getTo().file() == 'g') {
                Position rookFrom = new Position('f', rank);
                Position rookTo = new Position('h', rank);
                Piece rook = getPieceAt(rookFrom);
                setPieceAt(rookTo, rook);
                setPieceAt(rookFrom, null);
                if (rook != null) {
                    rook.setPosition(rookTo);
                    rook.setHasMoved(move.getMovedPiece().hasMoved());
                }
            } else if (move.getTo().file() == 'c') {
                Position rookFrom = new Position('d', rank);
                Position rookTo = new Position('a', rank);
                Piece rook = getPieceAt(rookFrom);
                setPieceAt(rookTo, rook);
                setPieceAt(rookFrom, null);
                if (rook != null) {
                    rook.setPosition(rookTo);
                    rook.setHasMoved(false);
                }
            }
        }
        if (!moveHistory.isEmpty() && moveHistory.getLast().equals(move)) {
            moveHistory.removeLast();
        }
        whiteToMove = !whiteToMove;
        enPassantTarget = null;
        if (!moveHistory.isEmpty()) {
            Move previous = moveHistory.getLast();
            PieceType type = previous.getMovedPiece().getType();
            boolean isPawnDoubleStep = type == PieceType.PAWN &&
                    Math.abs(previous.getFrom().rank() - previous.getTo().rank()) == 2;
            if (isPawnDoubleStep) {
                enPassantTarget = new Position(previous.getFrom().file(),
                        (previous.getFrom().rank() + previous.getTo().rank()) / 2);
            }
        }
    }
}
