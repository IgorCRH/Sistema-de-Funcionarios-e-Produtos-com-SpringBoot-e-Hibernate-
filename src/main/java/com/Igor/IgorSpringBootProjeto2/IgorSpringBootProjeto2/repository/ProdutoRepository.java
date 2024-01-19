package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Produtos;

public interface ProdutoRepository extends JpaRepository <Produtos, Long> {

	@Query("SELECT f FROM Produtos f WHERE f.nome = :nome")
	Produtos findByNome(@Param("nome") String nome);
	
	Optional<Produtos> findById(Long id);
	
	List<Produtos> findAll();
}
