package com.mateus.product.registration.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.product.registration.models.ProdutoEntity;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoCreateDto {

    private String nome;

    private BigDecimal preco;

    @Min(value = 50)
    private Integer quantidade;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    public ProdutoCreateDto(ProdutoEntity produtoEntity) {
        this.nome = produtoEntity.getNome();
        this.preco = produtoEntity.getPreco();
        this.quantidade = produtoEntity.getQuantidade();
        this.dataCriacao = produtoEntity.getDataCriacao();
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
