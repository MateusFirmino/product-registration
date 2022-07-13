package com.mateus.product.registration.controllers;

import com.mateus.product.registration.controllers.dto.ProdutoFindByIdDto;
import com.mateus.product.registration.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoFindByIdDto> findById(@PathVariable Long id) {
        final var produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }
}
