package de.andimoo5.chess.model;

public record Position(char file, int rank) {
    public Position {
        if (file < 'a' || file > 'h') {
            throw new IllegalArgumentException("File must be between 'a' and 'h'.");
        }
        if (rank < 1 || rank > 8) {
            throw new IllegalArgumentException("Rank must be between 1 and 8.");
        }
    }

    public int getFileIndex() {
        return file - 'a';
    }

    public int getRankIndex() {
        return rank - 1;
    }

    public static Position getFromString(String notation) {
        if (notation == null || notation.length() != 2) {
            throw new IllegalArgumentException("Invalid Notation: " + notation);
        }
        char file = Character.toLowerCase(notation.charAt(0));
        int rank = notation.charAt(1) - '0';
        if (file < 'a' || file > 'h') {
            throw new IllegalArgumentException("File must be between 'a' and 'h'.");
        }
        if (rank < 1 || rank > 8) {
            throw new IllegalArgumentException("Rank must be between 1 and 8.");
        }
        return new Position(file, rank);
    }

    @Override
    public String toString() {
        return "" + file + rank;
    }
}
