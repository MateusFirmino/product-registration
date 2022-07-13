package com.mateus.product.registration.services;

import com.mateus.product.registration.dto.ProdutoCreateDto;
import com.mateus.product.registration.dto.ProdutoFindByParameterDto;
import com.mateus.product.registration.enumarator.AplicacaoMensagemEnum;
import com.mateus.product.registration.exceptions.RegistroNaoEncontradoException;
import com.mateus.product.registration.models.ProdutoEntity;
import com.mateus.product.registration.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private static final String NAO_ENCONTRADO = AplicacaoMensagemEnum.X0_NAO_ENCONTRADO.trataMensagem("Produto");
    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoFindByParameterDto findById(Long id) {
        final var produto = repository.findByIdDto(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(NAO_ENCONTRADO));
        return new ProdutoFindByParameterDto(produto);
    }

    public ProdutoFindByParameterDto findByName(String nome) {
        final var produto = repository.findByName(nome)
                .orElseThrow(() -> new RegistroNaoEncontradoException(NAO_ENCONTRADO));
        return new ProdutoFindByParameterDto(produto);
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
