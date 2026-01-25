package br.com.api.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.api.dto.*;

@Repository
public class TagRepository {

        private final JdbcTemplate jdbc;

        public TagRepository(JdbcTemplate jdbc) {
                this.jdbc = jdbc;
        }

        public List<TagDto> listarTags() {
                return jdbc.query(
                                "SELECT id, nome, tipo, endereco FROM automacao.tags ORDER BY nome",
                                (rs, i) -> new TagDto(
                                                rs.getInt("id"),
                                                rs.getString("nome"),
                                                rs.getString("tipo"),
                                                rs.getInt("endereco")));
        }

        public UltimoValorDto ultimoValor(Integer tagId) {
                return jdbc.queryForObject(
                                "SELECT tag_id, tag_nome, valor_int, valor_bool, ts " +
                                                "FROM web.vw_tags_ultimo_valor WHERE tag_id = ?",
                                (rs, i) -> new UltimoValorDto(
                                                rs.getInt("tag_id"),
                                                rs.getString("tag_nome"),
                                                rs.getInt("valor_int"),
                                                rs.getObject("valor_bool", Boolean.class),
                                                rs.getObject("ts", java.time.OffsetDateTime.class)),
                                tagId);
        }

        public List<HistoricoDto> historico(Integer tagId) {
                return jdbc.query(
                                "SELECT ts, valor_int, valor_bool " +
                                                "FROM web.vw_historico_tags WHERE tag_id = ? ORDER BY ts",
                                (rs, i) -> new HistoricoDto(
                                                rs.getObject("ts", java.time.OffsetDateTime.class),
                                                rs.getInt("valor_int"),
                                                rs.getObject("valor_bool", Boolean.class)),
                                tagId);
        }

        public UltimoValorDto buscarUltimoValor(String tag) {
                return jdbc.queryForObject(
                                "SELECT tag_id, tag_nome, valor_int, valor_bool, ts " +
                                                "FROM web.vw_tags_ultimo_valor WHERE tag_nome = ?",
                                (rs, i) -> new UltimoValorDto(
                                                rs.getInt("tag_id"),
                                                rs.getString("tag_nome"),
                                                rs.getInt("valor_int"),
                                                rs.getObject("valor_bool", Boolean.class),
                                                rs.getObject("ts", java.time.OffsetDateTime.class)),
                                tag);
        }

        public List<TagDto> buscarTagsPorNome(String nome) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'buscarTagsPorNome'");
        }

}
