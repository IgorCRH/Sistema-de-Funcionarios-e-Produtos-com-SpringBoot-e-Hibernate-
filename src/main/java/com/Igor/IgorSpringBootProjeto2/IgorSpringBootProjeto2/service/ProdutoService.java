package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Produtos;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.repository.ProdutoRepository;


@Service
public class ProdutoService {

@Autowired
private final ProdutoRepository produtoRepository;

public ProdutoService(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
}

@Transactional(readOnly = true)
public Produtos pesquisarPorNome(String nome) {
    return produtoRepository.findByNome(nome);
}

@Transactional(readOnly = true)
public Optional<Produtos> pesquisarPorId(Long id) {
    return produtoRepository.findById(id);
}

@Transactional(readOnly = true)
public List<Produtos> listarTodos() {
    return produtoRepository.findAll();
}
}
