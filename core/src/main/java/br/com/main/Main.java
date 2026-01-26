package br.com.main;

import br.com.main.modbus.ModbusServer;
import br.com.main.control.ControlLogic;
import br.com.main.db.TagSchemaInitializer;
import br.com.main.io.FieldSimulator;
import br.com.main.io.FieldSimulatorDb;

// Classe principal do sistema industrial Java
public class Main {

    public static void main(String[] args) {
        // Inicia o servidor Modbus
        ModbusServer.start();
        // Inicializa o esquema de tags no banco de dados
        TagSchemaInitializer.init(); // 🔥 AQUI
        // Inicia a lógica de controle e os simuladores de campo em threads separadas
        new Thread(new ControlLogic()).start();
        // Inicia o simulador de campo
        new Thread(new FieldSimulator()).start();
        // Inicia o simulador de campo para banco de dados
        new Thread(new FieldSimulatorDb()).start();

        System.out.println("Sistema industrial Java iniciado");
    }
}
