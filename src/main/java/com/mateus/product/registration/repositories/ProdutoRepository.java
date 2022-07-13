package com.mateus.product.registration.repositories;

import com.mateus.product.registration.models.ProdutoEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    Connection con;
    public void abrirConexao() {
        try {
            con = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fecharConexao() {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<ProdutoEntity> findByIdDto(Long id) {
        abrirConexao();
        List<ProdutoEntity> produtos = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            String selectSql = "SELECT \"ID\", \"NOME\", \"PRECO\", \"QUANTIDADE\", \"DATA_CRIACAO\"\n" +
                    "\tFROM public.\"PRODUTO\"";
            try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    ProdutoEntity produto = new ProdutoEntity(
                            resultSet.getLong("ID"),
                            resultSet.getString("NOME"),
                            resultSet.getBigDecimal("PRECO"),
                            resultSet.getInt("QUANTIDADE"),
                            resultSet.getDate("DATA_CRIACAO"));
                    produtos.add(produto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        return produtos.isEmpty() == true ? Optional.empty() : Optional.of(produtos.get(0));
    }
}
