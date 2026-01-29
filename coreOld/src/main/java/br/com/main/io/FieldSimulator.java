package br.com.main.io;

import br.com.main.tags.CoilAddress;
import br.com.main.tags.HoldingRegister;
import br.com.main.tags.Tags;

public class FieldSimulator implements Runnable {

    private boolean running = true;

    @Override
    public void run() {

        System.out.println("FieldSimulator iniciado");

        int contador = 0;

        while (running) {

            try {
                // ====== LEITURA DE COILS (COMANDOS) ======

                boolean liga = Tags.coil(CoilAddress.CMD_LIGA).isSet();
                boolean reset = Tags.coil(CoilAddress.RESET_CONTADOR).isSet();
                boolean auto = Tags.coil(CoilAddress.MODO_AUTOMATICO).isSet();
                boolean emergencia = Tags.coil(CoilAddress.EMERGENCIA).isSet();

                // ====== LÓGICA DE CONTROLE ======

                if (emergencia) {
                    contador = 0;
                    Tags.coil(CoilAddress.CMD_LIGA).set(false);
                }

                if (reset) {
                    contador = 0;
                }

                if (liga && auto && !emergencia) {
                    contador++;
                }

                // ====== SIMULA PROCESSO ANALÓGICO ======

                int setpoint = Tags.hr(HoldingRegister.SETPOINT).getValue();

                int temperatura = Tags.hr(HoldingRegister.TEMPERATURA).getValue();
                int pressao = Tags.hr(HoldingRegister.PRESSAO).getValue();

                // Temperatura sobe se ligado
                if (liga && !emergencia) {
                    temperatura += 1;
                } else {
                    temperatura -= 1;
                }

                // Pressão acompanha setpoint
                if (pressao < setpoint) {
                    pressao++;
                } else if (pressao > setpoint) {
                    pressao--;
                }

                // Limites físicos
                temperatura = Math.max(0, Math.min(temperatura, 200));
                pressao = Math.max(0, Math.min(pressao, 300));

                // ====== ESCRITA NOS REGISTRADORES ======

                Tags.hr(HoldingRegister.CONTADOR).setValue(contador);
                Tags.hr(HoldingRegister.TEMPERATURA).setValue(temperatura);
                Tags.hr(HoldingRegister.PRESSAO).setValue(pressao);

                // ====== CICLO DE VARREDURA (CLP) ======
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
