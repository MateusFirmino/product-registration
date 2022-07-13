package com.mateus.product.registration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.product.registration.models.ProdutoEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoShowDto {

    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    public ProdutoShowDto(ProdutoEntity produtoEntity) {
        this.id = produtoEntity.getId();
        this.nome = produtoEntity.getNome();
        this.preco = produtoEntity.getPreco();
        this.quantidade = produtoEntity.getQuantidade();
        this.dataCriacao = produtoEntity.getDataCriacao();
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
