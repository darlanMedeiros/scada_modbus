package br.com.main.tags;

import java.util.EnumMap;

import net.wimpi.modbus.procimg.DigitalIn;
import net.wimpi.modbus.procimg.DigitalOut;
import net.wimpi.modbus.procimg.Register;

public final class Tags {

    private Tags() {
    }

    // ================= MAPS =================

    private static final EnumMap<CoilAddress, DigitalOut> COILS = new EnumMap<>(CoilAddress.class);

    private static final EnumMap<InputAddress, DigitalIn> INPUTS = new EnumMap<>(InputAddress.class);

    private static final EnumMap<HoldingRegister, Register> HOLDING = new EnumMap<>(HoldingRegister.class);

    private static final EnumMap<InputRegister, Register> INPUT_REGS = new EnumMap<>(InputRegister.class);

    // ================= BIND METHODS =================

    public static void bind(CoilAddress e, DigitalOut d) {
        COILS.put(e, d);
    }

    public static void bind(InputAddress e, DigitalIn d) {
        INPUTS.put(e, d);
    }

    public static void bind(HoldingRegister e, Register r) {
        HOLDING.put(e, r);
    }

    public static void bind(InputRegister e, Register r) {
        INPUT_REGS.put(e, r);
    }

    // ================= GETTERS =================

    public static DigitalOut coil(CoilAddress e) {
        return COILS.get(e);
    }

    public static DigitalIn input(InputAddress e) {
        return INPUTS.get(e);
    }

    public static Register hr(HoldingRegister e) {
        return HOLDING.get(e);
    }

    public static Register ir(InputRegister e) {
        return INPUT_REGS.get(e);
    }
}
