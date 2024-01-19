package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Funcionarios;

public interface FuncionarioRepository extends JpaRepository <Funcionarios, Long> {

	@Query("SELECT f FROM Funcionarios f LEFT JOIN FETCH f.cargo WHERE f.cpf = :cpf")
	Funcionarios findByCpf(@Param("cpf") String cpf);
	
	List<Funcionarios> findAll();
	
	@Query("SELECT f FROM Funcionarios f LEFT JOIN FETCH f.cargo WHERE f.nome = :nome")
	Funcionarios findByNome(@Param("nome") String nome);
}
