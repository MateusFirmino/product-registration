package com.mateus.product.registration.services;

import com.mateus.product.registration.controllers.dto.ProdutoFindByIdDto;
import com.mateus.product.registration.exceptions.NegocioException;
import com.mateus.product.registration.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoFindByIdDto findById(Long id) {
        final var produto = repository.findByIdDto(id)
                .orElseThrow(() -> new NegocioException("Produto com Id " + id + " não encontrado"));
        return new ProdutoFindByIdDto(produto);
    }
}
