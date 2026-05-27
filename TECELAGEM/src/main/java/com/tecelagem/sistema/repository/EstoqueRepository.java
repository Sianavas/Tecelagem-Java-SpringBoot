package com.tecelagem.sistema.repository;

import com.tecelagem.sistema.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByCodigoProduto(String codigoProduto);

}
