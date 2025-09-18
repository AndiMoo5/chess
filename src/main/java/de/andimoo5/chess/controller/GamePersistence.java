package de.andimoo5.chess.controller;

import de.andimoo5.chess.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GamePersistence {
    private final ObjectMapper mapper = new ObjectMapper();

    public void saveGame(GameState gameState, File file, GameMode gameMode, long whiteTimeRemaining,
                         long blackTimeRemaining, String playerWhiteName, String playerBlackName, Theme theme,
                         boolean showLegalMoves) throws IOException {
        List<MoveDTO> moveDTOs = new ArrayList<>();
        for (Move move : gameState.getMoveHistory()) {
            moveDTOs.add(MoveDTO.fromMove(move));
        }
        GameSave save = new GameSave(moveDTOs, gameState.isWhiteToMove(), gameMode, whiteTimeRemaining,
                blackTimeRemaining, playerWhiteName, playerBlackName, theme, showLegalMoves);
        mapper.writeValue(file, save);
    }

    public GameState loadGame(File file) throws IOException {
        GameSave save = mapper.readValue(file, GameSave.class);
        GameState state = new GameState();
        Board board = state.getBoard();
        board.initializeBoard();
        for (MoveDTO dto : save.moves) {
            Move move = dto.toMove(board);
            if (!state.isLegalMove(move)) {
                throw new IllegalStateException("Illegal move in save file: "+ move);
            }
            board.makeMove(move);
        }
        return state;
    }
}
