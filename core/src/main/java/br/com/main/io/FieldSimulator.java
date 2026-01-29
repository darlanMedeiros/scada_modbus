package br.com.main.io;

import br.com.main.control.process.ProcessLogic;

public class FieldSimulator implements Runnable {

    private final ProcessLogic process = new ProcessLogic();
    private boolean running = true;

    @Override
    public void run() {

        System.out.println("FieldSimulator iniciado");

        while (running) {
            process.executeCycle();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                stop();
            }
        }
    }

    public void stop() {
        running = false;
    }
}