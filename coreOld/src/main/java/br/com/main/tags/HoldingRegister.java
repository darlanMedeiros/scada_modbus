package br.com.main.tags;

public enum HoldingRegister {
    // ====== HOLDING REGISTERS (40001 - 49999) ======
    CONTADOR(40001),
    SETPOINT(40002),
    TEMPERATURA(40003),
    PRESSAO(40031);

    private final int address;

    // Construtor
    HoldingRegister(int address) {
        this.address = address;
    }

    public int address() {
        return address;
    }

    public int index() {
        return address - 40001;
    }
}
