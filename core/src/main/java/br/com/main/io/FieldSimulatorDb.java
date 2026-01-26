package br.com.main.io;

import br.com.main.db.TagDataLogger;
import br.com.main.db.TagRepository;
import br.com.main.tags.*;

public class FieldSimulatorDb implements Runnable {

    @Override
    public void run() {

        int tempId = TagRepository.getTagId(
                HoldingRegister.TEMPERATURA.name(), "HOLDING");

        int setpointId = TagRepository.getTagId(
                HoldingRegister.SETPOINT.name(), "HOLDING");

        int contadorId = TagRepository.getTagId(
                HoldingRegister.CONTADOR.name(), "HOLDING");

        int pressaoId = TagRepository.getTagId(
                HoldingRegister.PRESSAO.name(), "HOLDING");

        int ligaId = TagRepository.getTagId(
                CoilAddress.CMD_LIGA.name(), "COIL");
        int emergenciaId = TagRepository.getTagId(
                CoilAddress.EMERGENCIA.name(), "COIL");
        int resetId = TagRepository.getTagId(
                CoilAddress.RESET_CONTADOR.name(), "COIL");
        int modoAutoId = TagRepository.getTagId(
                CoilAddress.MODO_AUTOMATICO.name(), "COIL");

        while (true) {

            int temp = Tags.hr(HoldingRegister.TEMPERATURA).getValue();
            int pressao = Tags.hr(HoldingRegister.PRESSAO).getValue();
            boolean liga = Tags.coil(CoilAddress.CMD_LIGA).isSet();
            int setpoint = Tags.hr(HoldingRegister.SETPOINT).getValue();
            int contador = Tags.hr(HoldingRegister.CONTADOR).getValue();
            boolean emergencia = Tags.coil(CoilAddress.EMERGENCIA).isSet();
            boolean reset = Tags.coil(CoilAddress.RESET_CONTADOR).isSet();
            boolean modoAuto = Tags.coil(CoilAddress.MODO_AUTOMATICO).isSet();

            // 🔥 grava SOMENTE se mudar
            TagDataLogger.logIntIfChanged(tempId, temp);
            TagDataLogger.logIntIfChanged(pressaoId, pressao);
            TagDataLogger.logBoolIfChanged(ligaId, liga);
            TagDataLogger.logBoolIfChanged(emergenciaId, emergencia);
            TagDataLogger.logBoolIfChanged(resetId, reset);
            TagDataLogger.logBoolIfChanged(modoAutoId, modoAuto);
            TagDataLogger.logIntIfChanged(setpointId, setpoint);
            TagDataLogger.logIntIfChanged(contadorId, contador);

            try {
                Thread.sleep(500); // polling
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
