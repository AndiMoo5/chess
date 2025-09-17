package de.andimoo5.chess.controller;

public class PlayerTimer {
    private long remainingMillis;
    private long lastStartTime;
    private boolean running;

    public PlayerTimer(long totalMillis) {
        this.remainingMillis = totalMillis;
    }

    public void start() {
        if (!running) {
            lastStartTime = System.currentTimeMillis();
            running = true;
        }
    }

    public void stop() {
        if (running) {
            long now = System.currentTimeMillis();
            remainingMillis -= (now - lastStartTime);
            running = false;
        }
    }

    public long getRemainingMillis() {
        if (running) {
            long now = System.currentTimeMillis();
            return remainingMillis - (now - lastStartTime);
        }
        return remainingMillis;
    }

    public boolean isTimeUp() {
        return getRemainingMillis() <= 0;
    }
}
