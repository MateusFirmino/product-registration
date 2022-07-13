package com.mateus.product.registration.repositories;

import com.mateus.product.registration.models.ProdutoEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    Connection con;

    public void abrirConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "123");
        } catch (SQLException | ClassNotFoundException e) {
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

    public ProdutoEntity create(ProdutoEntity produto) {
        abrirConexao();
        String sql = "INSERT INTO public.\"PRODUTO\"(\"NOME\", \"PRECO\", \"QUANTIDADE\", \"DATA_CRIACAO\")VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, produto.getNome());
            pstm.setBigDecimal(2, produto.getPreco());
            pstm.setInt(3, produto.getQuantidade());
            pstm.setDate(4, Date.valueOf(produto.getDataCriacao()));
            pstm.execute();
            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    produto.setId(rst.getLong(1));
                }
            }
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ProdutoEntity(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getDataCriacao()
        );
    }

    public ProdutoEntity update(Long id, ProdutoEntity produto) {
        abrirConexao();
        String sql = "UPDATE public.\"PRODUTO\" SET \"NOME\"=?, \"PRECO\"=?, \"QUANTIDADE\"=?, \"DATA_CRIACAO\"=? WHERE \"ID\" =? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDate(4, Date.valueOf(produto.getDataCriacao()));
            stmt.setLong(5, id);
            stmt.execute();
            fecharConexao();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ProdutoEntity(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getDataCriacao()
        );
    }

    public Optional<ProdutoEntity> findByIdDto(Long id) {
        abrirConexao();
        try {
            String sql = "SELECT * FROM public.\"PRODUTO\" WHERE \"ID\" =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next())
                return Optional.empty();
            ProdutoEntity produto = new ProdutoEntity();
            produto.setId(rs.getLong("ID"));
            produto.setNome(rs.getString("NOME"));
            produto.setPreco(rs.getBigDecimal("PRECO"));
            produto.setQuantidade(rs.getInt("QUANTIDADE"));
            produto.setDataCriacao(LocalDate.parse(rs.getDate("DATA_CRIACAO").toString()));
            stmt.close();
            rs.close();
            fecharConexao();
            return Optional.of(produto);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<ProdutoEntity> findByName(String nome) {
        abrirConexao();
        try {
            String sql = "\n" +
                    "SELECT \"ID\", \"NOME\", \"PRECO\", \"QUANTIDADE\", \"DATA_CRIACAO\" FROM public.\"PRODUTO\" WHERE UPPER(\"NOME\") = UPPER(?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            ProdutoEntity produto = new ProdutoEntity();
            if (!rs.next())
                return Optional.empty();
            produto.setId(rs.getLong("ID"));
            produto.setNome(rs.getString("NOME"));
            produto.setPreco(rs.getBigDecimal("PRECO"));
            produto.setQuantidade(rs.getInt("QUANTIDADE"));
            produto.setDataCriacao(LocalDate.parse(rs.getDate("DATA_CRIACAO").toString()));
            stmt.close();
            rs.close();
            fecharConexao();
            return Optional.of(produto);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Long id) {
        abrirConexao();
        try (PreparedStatement stm = con.prepareStatement("DELETE FROM public.\"PRODUTO\" WHERE \"ID\" =?")) {
            stm.setLong(1, id);
            stm.execute();
            fecharConexao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
