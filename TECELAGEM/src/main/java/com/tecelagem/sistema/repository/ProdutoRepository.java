package com.tecelagem.sistema.repository;

import com.tecelagem.sistema.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCodigo(String codigo);

    List<Produto> findByTipo(String tipo);

}
