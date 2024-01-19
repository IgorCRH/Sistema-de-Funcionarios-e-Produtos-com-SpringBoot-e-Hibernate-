package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.DAO;
import java.math.BigDecimal;
import java.util.List;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Produtos;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutosDAO {

	    private final EntityManager entityManager;

	    public ProdutosDAO(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }
	    
	    @Transactional
	    public void adicionarProduto(Produtos produto) {
	        entityManager.persist(produto);
	    }

	    @Transactional
	    public void atualizarQuantidade(Produtos produto, int novaQuantidade) {
	        produto.setQuantidade(novaQuantidade);
	        atualizar(produto);
	    }
	    
	    @Transactional
	    public void atualizarValorUnitario(Produtos produto, BigDecimal novovalorunitario) {
	        produto.setValorunitario(novovalorunitario);
	        atualizar(produto);
	    }
	    
	    @Transactional
	    public void atualizar(Produtos produto) {
	        entityManager.merge(produto);
	    }

        @Transactional
	    public void remover(Produtos produto) {
	        entityManager.remove(produto);
	    }

	    public List<Produtos> listarTodos() {
	        TypedQuery<Produtos> query = entityManager.createQuery("SELECT p FROM Produtos p", Produtos.class);
	        return query.getResultList();
	    }
	    
}
