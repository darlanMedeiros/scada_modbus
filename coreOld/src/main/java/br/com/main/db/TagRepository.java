package br.com.main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class TagRepository {

    private static final Map<String, Integer> CACHE = new HashMap<>();

    public static int getTagId(String nome, String tipo) {

        String key = tipo + ":" + nome;
        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        }

        String sql = "SELECT id FROM automacao.tags " +
                "WHERE nome = ? AND tipo = ?";

        try (Connection c = Database.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, tipo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                CACHE.put(key, id);
                return id;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("TAG não encontrada no banco: " + key);
    }
}
