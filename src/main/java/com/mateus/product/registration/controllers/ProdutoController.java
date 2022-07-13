package com.mateus.product.registration.controllers;

import com.mateus.product.registration.dto.ProdutoCreateDto;
import com.mateus.product.registration.dto.ProdutoFindByParameterDto;
import com.mateus.product.registration.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoFindByParameterDto> searchById(@PathVariable Long id) {
        final var produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/nome")
    public ResponseEntity<ProdutoFindByParameterDto> searchByName(@RequestParam(required = true) String nome) {
        final var produto = produtoService.findByName(nome);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoCreateDto> update(@PathVariable Long id, @RequestBody @Valid ProdutoCreateDto produtoUpdateDto) {
        final var produto = produtoService.updateProduto(id, produtoUpdateDto.toProdutoEntity());
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoCreateDto> store(@RequestBody @Valid ProdutoCreateDto produtoCreateDTO) {
        final var produto = produtoService.createProduto(produtoCreateDTO.toProdutoEntity());
        return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
