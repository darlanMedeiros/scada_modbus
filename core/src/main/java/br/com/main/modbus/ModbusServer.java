package br.com.main.modbus;

import java.util.Arrays;

import br.com.main.tags.CoilAddress;
import br.com.main.tags.HoldingRegister;
import br.com.main.tags.InputAddress;
import br.com.main.tags.InputRegister;
import br.com.main.tags.Tags;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.net.ModbusTCPListener;
import net.wimpi.modbus.procimg.DigitalOut;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleDigitalIn;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;

public class ModbusServer {

    public static void start() {

        SimpleProcessImage spi = new SimpleProcessImage();

        // ================== DISCRETE INPUTS (1xxxx) ==================

        int maxInputAddress = Arrays.stream(InputAddress.values())
                .mapToInt(InputAddress::address)
                .max()
                .orElse(10001);

        int qtdInputs = maxInputAddress - 10001 + 1;

        for (int i = 0; i < qtdInputs; i++) {
            spi.addDigitalIn(new SimpleDigitalIn(false));
        }

        for (InputAddress in : InputAddress.values()) {
            Tags.bind(in, spi.getDigitalIn(in.index()));
        }

        // ================== INPUT REGISTERS (3xxxx) ==================

        int maxIrAddress = Arrays.stream(InputRegister.values())
                .mapToInt(InputRegister::address)
                .max()
                .orElse(30001);

        int qtdIR = maxIrAddress - 30001 + 1;

        for (int i = 0; i < qtdIR; i++) {
            spi.addInputRegister(new SimpleRegister(0));
        }

        for (InputRegister ir : InputRegister.values()) {
            Register r = (Register) spi.getInputRegister(ir.index());
            Tags.bind(ir, r);
        }

        // ================== COILS (0xxxx) ==================

        int maxCoilAddress = Arrays.stream(CoilAddress.values())
                .mapToInt(CoilAddress::address)
                .max()
                .orElse(1);

        for (int i = 0; i < maxCoilAddress; i++) {
            spi.addDigitalOut(new SimpleDigitalOut(false));
        }

        for (CoilAddress coil : CoilAddress.values()) {
            DigitalOut d = spi.getDigitalOut(coil.index());
            Tags.bind(coil, d);
        }

        // ================== HOLDING REGISTERS (4xxxx) ==================

        int maxHrAddress = Arrays.stream(HoldingRegister.values())
                .mapToInt(HoldingRegister::address)
                .max()
                .orElse(40001);

        int qtdHR = maxHrAddress - 40001 + 1;

        for (int i = 0; i < qtdHR; i++) {
            spi.addRegister(new SimpleRegister(0));
        }

        for (HoldingRegister hr : HoldingRegister.values()) {
            Register r = spi.getRegister(hr.index());
            Tags.bind(hr, r);
        }

        // ===== Valores iniciais =====
        Tags.hr(HoldingRegister.CONTADOR).setValue(0);
        Tags.hr(HoldingRegister.SETPOINT).setValue(50);
        Tags.hr(HoldingRegister.TEMPERATURA).setValue(25);
        Tags.hr(HoldingRegister.PRESSAO).setValue(100);

        // ================= MODBUS ==================
        ModbusCoupler.getReference().setProcessImage(spi);
        ModbusCoupler.getReference().setMaster(false);
        ModbusCoupler.getReference().setUnitID(1);

        ModbusTCPListener listener = new ModbusTCPListener(1);
        listener.setPort(1502);
        listener.start();

        System.out.println("Modbus TCP iniciado (arquitetura industrial completa)");
    }
}
