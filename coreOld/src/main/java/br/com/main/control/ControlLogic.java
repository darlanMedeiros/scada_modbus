package br.com.main.control;

public class ControlLogic implements Runnable {

    @Override
    public void run() {
        while (true) {
            // lógica do CLP
            // System.out.println("Scan CLP executando");
            sleep(100);
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
