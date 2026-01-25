package br.com.main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JdbcLogger {

    private static final String SQL = "INSERT INTO historico.historico_tags " +
            "(tag_id, valor_int, valor_bool) " +
            "VALUES (?, ?, ?)";

    public static void logInt(int tagId, int value) {
        try (Connection c = Database.getConnection();
                PreparedStatement ps = c.prepareStatement(SQL)) {

            ps.setInt(1, tagId);
            ps.setInt(2, value);
            ps.setNull(3, java.sql.Types.BOOLEAN);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logBool(int tagId, boolean value) {
        try (Connection c = Database.getConnection();
                PreparedStatement ps = c.prepareStatement(SQL)) {

            ps.setInt(1, tagId);
            ps.setNull(2, java.sql.Types.INTEGER);
            ps.setBoolean(3, value);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
