package br.com.main;

import br.com.main.modbus.ModbusServer;
import br.com.main.control.ControlLogic;
import br.com.main.db.TagSchemaInitializer;
import br.com.main.io.FieldSimulator;
import br.com.main.io.FieldSimulatorDb;

public class Main {

    public static void main(String[] args) {

        ModbusServer.start();
        TagSchemaInitializer.init(); // 🔥 AQUI

        new Thread(new ControlLogic()).start();
        new Thread(new FieldSimulator()).start();
        new Thread(new FieldSimulatorDb()).start();

        System.out.println("Sistema industrial Java iniciado");
    }
}
