package br.com.main.control.process;

import br.com.main.tags.CoilAddress;
import br.com.main.tags.HoldingRegister;
import br.com.main.tags.Tags;

public class ProcessLogic {

    private int contador = 0;

    public void executeCycle() {

        boolean liga = Tags.coil(CoilAddress.CMD_LIGA).isSet();
        boolean reset = Tags.coil(CoilAddress.RESET_CONTADOR).isSet();
        boolean auto = Tags.coil(CoilAddress.MODO_AUTOMATICO).isSet();
        boolean emergencia = Tags.coil(CoilAddress.EMERGENCIA).isSet();

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

        int setpoint = Tags.hr(HoldingRegister.SETPOINT).getValue();
        int temperatura = Tags.hr(HoldingRegister.TEMPERATURA).getValue();
        int pressao = Tags.hr(HoldingRegister.PRESSAO).getValue();

        temperatura += (liga && !emergencia) ? 1 : -1;

        if (pressao < setpoint)
            pressao++;
        else if (pressao > setpoint)
            pressao--;

        temperatura = Math.max(0, Math.min(temperatura, 200));
        pressao = Math.max(0, Math.min(pressao, 300));

        Tags.hr(HoldingRegister.CONTADOR).setValue(contador);
        Tags.hr(HoldingRegister.TEMPERATURA).setValue(temperatura);
        Tags.hr(HoldingRegister.PRESSAO).setValue(pressao);
    }
}