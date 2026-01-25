package br.com.main.tags;

/**
 * Input Registers (3xxxx)
 */
public enum InputRegister {

    VAZAO(30001),
    TENSAO(30002),
    CORRENTE(30003),
    VELOCIDADE(30010);

    private final int address;

    InputRegister(int address) {
        this.address = address;
    }

    public int address() {
        return address;
    }

    /** índice interno JAMOD */
    public int index() {
        return address - 30001;
    }
}
