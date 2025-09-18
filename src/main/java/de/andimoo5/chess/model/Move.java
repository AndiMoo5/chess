package de.andimoo5.chess.model;

public class Move {
    private final Position from;
    private final Position to;
    private final Piece movedPiece;
    private final Piece capturedPiece;
    private final boolean isPromotion;
    private final PieceType promotionType;
    private final boolean isCastling;
    private final boolean isEnPassant;
    private final int moveNumber;

    public Move(Position from, Position to, Piece movedPiece, Piece capturedPiece, boolean isPromotion,
                PieceType promotionType, boolean isCastling, boolean isEnPassant, int moveNumber) {
        this.from = from;
        this.to = to;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
        this.isPromotion = isPromotion;
        this.promotionType = promotionType;
        this.isCastling = isCastling;
        this.isEnPassant = isEnPassant;
        this.moveNumber = moveNumber;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public PieceType getPromotionType() {
        return promotionType;
    }

    public boolean isCastling() {
        return isCastling;
    }

    public boolean isEnPassant() {
        return isEnPassant;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public String toAlgebraicNotation() {
        if (isCastling) {
            return to.file() == 'g' ? "0-0" : "0-0-0";
        }
        StringBuilder sb = new StringBuilder();
        PieceType type = movedPiece.getType();
        boolean isPawn = type == PieceType.PAWN;
        // Piece letter (skip for pawn)
        if (!isPawn) {
            sb.append(type.toString().charAt(0));
        }
        // Pawn capture needs file of origin
        if (isPawn && capturedPiece != null) {
            sb.append(from.file());
        }
        // Capture marker
        if (capturedPiece != null) {
            sb.append('x');
        }
        // Destination square
        sb.append(to.toString());
        // Promotion
        if (isPromotion && promotionType != null) {
            sb.append('=').append(promotionType.toString().charAt(0));
        }
        // En Passant
        if (isEnPassant) {
            sb.append(" e.p.");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(movedPiece.getType()).append(" ");
        sb.append(from).append(" → ").append(to);
        if (capturedPiece != null) {
            sb.append(" captures ").append(capturedPiece.getType());
        }
        if (isPromotion) {
            sb.append(" promotes to ").append(promotionType);
        }
        if (isCastling) {
            sb.append(" (castling)");
        }
        if (isEnPassant) {
            sb.append(" (en passant)");
        }
        return sb.toString();
    }

    @Override
    public Move clone() {
        return new Move(
                new Position(from.file(), from.rank()),
                new Position(to.file(), to.rank()),
                PieceFactory.createClone(movedPiece),
                capturedPiece != null ? PieceFactory.createClone(capturedPiece) : null,
                isPromotion,
                promotionType,
                isCastling,
                isEnPassant,
                moveNumber
        );
    }
}
