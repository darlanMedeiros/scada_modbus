package br.com.main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.main.tags.*;

public class TagSchemaInitializer {

    private static final String SQL = "INSERT INTO automacao.tags (nome, tipo, endereco) " +
            "VALUES (?, ?, ?) " +
            "ON CONFLICT (tipo, endereco) DO NOTHING";

    public static void init() {

        try (Connection c = Database.getConnection();
                PreparedStatement ps = c.prepareStatement(SQL)) {

            // ===== HOLDING REGISTERS =====
            for (HoldingRegister hr : HoldingRegister.values()) {
                ps.setString(1, hr.name());
                ps.setString(2, "HOLDING");
                ps.setInt(3, hr.address());
                ps.executeUpdate();
            }

            // ===== INPUT REGISTERS =====
            for (InputRegister ir : InputRegister.values()) {
                ps.setString(1, ir.name());
                ps.setString(2, "INPUT_REGISTER");
                ps.setInt(3, ir.address());
                ps.executeUpdate();
            }

            // ===== COILS =====
            for (CoilAddress cAddr : CoilAddress.values()) {
                ps.setString(1, cAddr.name());
                ps.setString(2, "COIL");
                ps.setInt(3, cAddr.address());
                ps.executeUpdate();
            }

            // ===== DISCRETE INPUTS =====
            for (InputAddress in : InputAddress.values()) {
                ps.setString(1, in.name());
                ps.setString(2, "INPUT");
                ps.setInt(3, in.address());
                ps.executeUpdate();
            }

            System.out.println("Tags sincronizadas com o banco");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar tags", e);
        }
    }
}
