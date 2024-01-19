package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.DAO;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Cargo;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Funcionarios;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionariosDAO {

	private final EntityManager entityManager;

    public FuncionariosDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Transactional
    public void adicionarFuncionario(Funcionarios funcionario) {
        entityManager.persist(funcionario);

    }
    
    @Transactional(readOnly = true)
    public Cargo getCargoPorId(Long idCargo) {
        return entityManager.find(Cargo.class, idCargo);
    }

    
    @Transactional
    public void atualizarSalario(Funcionarios funcionario, Double novoSalario) {
        funcionario.setSalario(novoSalario);
        atualizar(funcionario);
    }

    @Transactional
    public void atualizar(Funcionarios funcionario) {
        entityManager.merge(funcionario);

    }

    @Transactional
    public void remover(Funcionarios funcionario) {
        entityManager.remove(funcionario);
    }

    @Transactional(readOnly = true)
    public Funcionarios buscarPorID(long id) {
        return entityManager.find(Funcionarios.class, id);
    }

    @Transactional(readOnly = true)
    public List<Funcionarios> listarTodos() {
        TypedQuery<Funcionarios> query = entityManager.createQuery("SELECT f FROM Funcionarios f", Funcionarios.class);
        return query.getResultList();
    }
    
    @Transactional(readOnly = true)
    public ArrayList<Funcionarios> getAllFuncionarios(EntityManager entityManager) {
        TypedQuery<Funcionarios> query = entityManager.createQuery("SELECT f FROM Funcionarios f", Funcionarios.class);
        return (ArrayList<Funcionarios>) query.getResultList();
    }
    
    @Transactional(readOnly = true)
    public Funcionarios pesquisarPorNome(String nome) {
        TypedQuery<Funcionarios> query = entityManager.createQuery("SELECT f FROM Funcionarios f WHERE f.nome = :nome", Funcionarios.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }
    
    public static Long getIDCargoPorNome(EntityManager entityManager, String nomeCargo) {
        TypedQuery<Long> querycargoGerente = entityManager.createQuery("SELECT c.IDCargo FROM Cargo c WHERE c.nomecargo = :nomeCargo", Long.class);
        querycargoGerente.setParameter("nomeCargo", nomeCargo);
        return querycargoGerente.getSingleResult();
    }
    
    public List<Cargo> getCargos() {
        TypedQuery<Cargo> query = entityManager.createQuery("SELECT c FROM Cargo c", Cargo.class);
        return query.getResultList();
    }
	
}
