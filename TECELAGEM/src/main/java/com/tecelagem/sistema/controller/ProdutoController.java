package com.tecelagem.sistema.controller;

import com.tecelagem.sistema.model.Produto;
import com.tecelagem.sistema.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }
    @GetMapping("/total")
    public long totalProdutos() {
    return produtoRepository.count();
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Produto alterar(@PathVariable Long id, @RequestBody Produto produto) {

        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow();

        produtoExistente.setCodigo(produto.getCodigo());
        produtoExistente.setTipo(produto.getTipo());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setTamanho(produto.getTamanho());
        produtoExistente.setPreco(produto.getPreco());

        return produtoRepository.save(produtoExistente);
    }
}