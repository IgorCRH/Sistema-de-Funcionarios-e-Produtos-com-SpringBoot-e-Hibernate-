package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.repository.FuncionarioRepository;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Funcionarios;

@Service
public class FuncionarioService {
	@Autowired
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional(readOnly = true)
    @EntityGraph(attributePaths = "cargo")
    public Funcionarios findbyCPF(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }
    
    @Transactional(readOnly = true)
    public List<Funcionarios> listarTodos() {
        return funcionarioRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Funcionarios pesquisarPorNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }
}

