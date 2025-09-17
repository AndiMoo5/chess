package de.andimoo5.chess.controller;

import de.andimoo5.chess.model.*;

public class GameController {
    private final GameState gameState;
    private final GameMode gameMode;
    private final AIPlayer aiPlayer;
    private PlayerTimer whiteTimer;
    private PlayerTimer blackTimer;

    public GameController(GameMode mode) {
        this.gameMode = mode;
        this.gameState = new GameState();
        this.aiPlayer = (gameMode != GameMode.HUMAN_VS_HUMAN) ? new AIPlayer() : null;
    }

    public void startGameWithTimer(long millisPerPlayer) {
        whiteTimer = new PlayerTimer(millisPerPlayer);
        blackTimer = new PlayerTimer(millisPerPlayer);
        whiteTimer.start();
    }

    private void switchTimers() {
        if (whiteTimer != null && blackTimer != null) {
            if (gameState.isWhiteToMove()) {
                blackTimer.stop();
                whiteTimer.start();
            } else {
                whiteTimer.stop();
                blackTimer.start();
            }
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean handleMove(Position from, Position to, PieceType chosenPromotion) {
        Piece piece = getGameState().getBoard().getPieceAt(from);
        if (piece == null || piece.isWhite() != gameState.isWhiteToMove()) return false;
        Move move = createMove(from, to, piece, chosenPromotion);
        if (!gameState.isLegalMove(move)) return false;
        gameState.applyMove(move);
        switchTimers();
        if (gameMode == GameMode.HUMAN_VS_AI && !gameState.isWhiteToMove()) {
            handleAIMove();
        } else if (gameMode == GameMode.AI_VS_AI) {
            while (gameState.getStatus() == GameStatus.ONGOING) {
                switchTimers();
                handleAIMove();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
        return true;
    }

    public boolean handleMove(Position from, Position to) {
        return handleMove(from, to, null);
    }

    private Move createMove(Position from, Position to, Piece piece, PieceType promotionType) {
        Piece captured = gameState.getBoard().getPieceAt(to);
        boolean isPromotion = piece.getType() == PieceType.PAWN &&
                (to.rank() == 8 || to.rank() == 1);
        if (!isPromotion) {
            promotionType = null;
        } else if (promotionType == null || promotionType == PieceType.PAWN || promotionType == PieceType.KING) {
            promotionType = PieceType.QUEEN;
        }
        boolean isCastling = piece.getType() == PieceType.KING &&
                Math.abs(from.file() - to.file()) == 2;
        boolean isEnPassant = piece.getType() == PieceType.PAWN &&
                to.equals(gameState.getBoard().getEnPassantTarget());
        return new Move(from, to, piece, captured, isPromotion, promotionType, isCastling,
                isEnPassant, gameState.getMoveHistory().size() + 1);
    }

    private void handleAIMove() {
        Move aiMove = aiPlayer.calculateMove(gameState.getBoard());
        if (aiMove != null && gameState.isLegalMove(aiMove)) {
            gameState.applyMove(aiMove);
        }
    }

    public void startNewGame() {
        gameState.getBoard().initializeBoard();
    }

    public GameStatus getStatus() {
        return gameState.getStatus();
    }
}
