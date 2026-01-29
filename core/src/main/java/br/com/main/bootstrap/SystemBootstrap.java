package br.com.main.bootstrap;

import br.com.main.control.ControlLogic;
import br.com.main.db.TagSchemaInitializer;
import br.com.main.io.FieldSimulator;
import br.com.main.io.FieldSimulatorDb;
import br.com.main.modbus.ModbusServer;

public class SystemBootstrap {

    public static void start() {

        ModbusServer.start();
        TagSchemaInitializer.init();

        new Thread(new ControlLogic(), "CONTROL-LOGIC").start();
        new Thread(new FieldSimulator(), "FIELD-SIM").start();
        new Thread(new FieldSimulatorDb(), "DB-LOGGER").start();

        System.out.println("Sistema industrial Java iniciado");
    }
}