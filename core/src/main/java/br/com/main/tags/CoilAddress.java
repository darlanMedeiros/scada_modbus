package br.com.main.tags;

public enum CoilAddress {
    // ====== COILS (00001 - 09999) ======
    CMD_LIGA(1),
    RESET_CONTADOR(2),
    MODO_AUTOMATICO(3),
    EMERGENCIA(10);

    private final int address;

    // Construtor
    CoilAddress(int address) {
        this.address = address;
    }

    public int address() {
        return address;
    }

    public int index() {
        return address - 1;
    }
}
