package com.clickescale.contatos.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

@Repository
public class ContatoRepository {
    private final JdbcTemplate jdbcTemplate;

    public ContatoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<ContatoDTO> buscarPorCpf(String cpf) {

        String sql = """
                SELECT nome, cpf, telefone
                FROM contatos
                WHERE REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', '') = ?
                LIMIT 1
                """;

        try {
            return jdbcTemplate.query(
                    sql,
                    ps -> ps.setString(1, cpf),
                    rs -> {
                        if (!rs.next()) {
                            return Optional.empty();
                        }

                        return Optional.of(
                                new ContatoDTO(
                                        rs.getString("nome"),
                                        rs.getString("cpf"),
                                        rs.getString("telefone")
                                )
                        );
                    }
            );
        } catch (DataAccessException e) {
            // Treat database exceptions (including SQLite "no rows" cases) as not found
            return Optional.empty();
        }
    }
}
