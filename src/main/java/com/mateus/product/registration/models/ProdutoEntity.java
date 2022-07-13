package com.mateus.product.registration.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ProdutoEntity {
    private Long id;

    private String nome;

    private BigDecimal preco;

    private Integer quantidade;

    private LocalDate dataCriacao;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Long id, String nome, BigDecimal preco, Integer quantidade, Date dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataCriacao = LocalDate.parse(dataCriacao.toString());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
