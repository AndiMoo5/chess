package de.andimoo5.chess.model;

public class MoveDTO {
    public String from;
    public String to;
    public String pieceType;
    public boolean isWhite;
    public boolean isPromotion;
    public String promotionType;
    public boolean isCastling;
    public boolean isEnPassant;

    public static MoveDTO fromMove(Move move) {
        MoveDTO dto = new MoveDTO();
        dto.from = move.getFrom().toString();
        dto.to = move.getTo().toString();
        dto.pieceType = move.getMovedPiece().getType().name();
        dto.isWhite = move.getMovedPiece().isWhite();
        dto.isPromotion = move.isPromotion();
        dto.promotionType = move.getPromotionType() != null ? move.getPromotionType().name() : null;
        dto.isCastling = move.isCastling();
        dto.isEnPassant = move.isEnPassant();
        return dto;
    }

    public Move toMove(Board board) {
        Position fromPos = Position.getFromString(from);
        Position toPos = Position.getFromString(to);
        Piece movedPiece = PieceFactory.create(PieceType.valueOf(pieceType), isWhite, fromPos);
        Piece capturedPiece = board.getPieceAt(toPos);
        PieceType promoType = (promotionType != null) ? PieceType.valueOf(promotionType) : null;
        int moveNumber = board.getMoveHistory().size() + 1;
        return new Move(fromPos, toPos, movedPiece, capturedPiece, isPromotion, promoType,
                isCastling, isEnPassant, moveNumber);
    }
}
