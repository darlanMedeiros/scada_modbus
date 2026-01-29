package br.com.main.util;

public class CycleTimer {

    private final long cycleMs;
    private long start;

    public CycleTimer(long cycleMs) {
        this.cycleMs = cycleMs;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void waitNext() {
        long elapsed = System.currentTimeMillis() - start;
        long sleep = cycleMs - elapsed;
        if (sleep > 0) {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ignored) {
            }
        }
    }
}