package br.com.main.tags;

/**
 * Discrete Inputs (1xxxx)
 */
public enum InputAddress {

    SENSOR_NIVEL(10001),
    SENSOR_PORTA(10002),
    SENSOR_FALHA(10003),
    SENSOR_PROXIMIDADE(10004);

    private final int address;

    InputAddress(int address) {
        this.address = address;
    }

    public int address() {
        return address;
    }

    /** índice interno JAMOD */
    public int index() {
        return address - 10001;
    }
}
