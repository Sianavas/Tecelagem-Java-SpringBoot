package com.tecelagem.sistema.controller;

import com.tecelagem.sistema.model.Estoque;
import com.tecelagem.sistema.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping
    public List<Estoque> listar() {
        return estoqueRepository.findAll();
    }

    @PostMapping
    public Estoque cadastrar(@RequestBody Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    @PutMapping("/{id}")
    public Estoque alterar(@PathVariable Long id, @RequestBody Estoque estoque) {

        Estoque estoqueExistente =
                estoqueRepository.findById(id).orElseThrow();

        estoqueExistente.setCodigoProduto(estoque.getCodigoProduto());
        estoqueExistente.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel());

        return estoqueRepository.save(estoqueExistente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        estoqueRepository.deleteById(id);
    }

    @GetMapping("/total")
    public long totalEstoque() {
        return estoqueRepository.count();
    }
}