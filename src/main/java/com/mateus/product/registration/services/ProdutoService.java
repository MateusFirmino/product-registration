package com.mateus.product.registration.services;

import com.mateus.product.registration.dto.ProdutoCreateDto;
import com.mateus.product.registration.dto.ProdutoFindByIdDto;
import com.mateus.product.registration.dto.ProdutoShowDto;
import com.mateus.product.registration.exceptions.NegocioException;
import com.mateus.product.registration.models.ProdutoEntity;
import com.mateus.product.registration.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoFindByIdDto findById(Long id) {
        final var produto = repository.findByIdDto(id)
                .orElseThrow(() -> new NegocioException("Produto com Id " + id + " não encontrado"));
        return new ProdutoFindByIdDto(produto);
    }

    public ProdutoShowDto findByName(String nome) {
        final var produto = repository.findByName(nome)
                .orElseThrow(() -> new NegocioException("Produto com Nome " + nome + " não encontrado"));
        return new ProdutoShowDto(produto);
    }

    public ProdutoCreateDto updateProduto(Long id, ProdutoEntity produto) {
        final var produtoResult = repository.update(id, produto);
        return new ProdutoCreateDto(produtoResult);
    }

    public ProdutoCreateDto createProduto(ProdutoEntity produto) {
        final var produtoResult = repository.create(produto);
        return new ProdutoCreateDto(produtoResult);
    }

    public void delete(Long id) {
        repository.findByIdDto(id);
        try {
            repository.deletar(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Produto não pode ser deletado! O cadastro possui registros ");
        }
    }


}
