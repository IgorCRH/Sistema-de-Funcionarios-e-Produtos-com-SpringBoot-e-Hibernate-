package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Produtos")
public class Produtos {

@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID")
private long ID;

@Column(name = "Nome")
private String nome;

@Column(name = "Quantidade")
private int quantidade;

@Column(name = "valor_unitario")
private BigDecimal valorunitario;

public Produtos(String nome, int quantidade, BigDecimal valorunitario) {
	super();
	this.nome = nome;
	this.quantidade = quantidade;
	this.valorunitario = valorunitario;
}

public Produtos() {
	super();
}

public long getID() {
	return ID;
}

public void setID(long iD) {
	ID = iD;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public int getQuantidade() {
	return quantidade;
}

public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
}

public BigDecimal getValorunitario() {
	return valorunitario;
}

public void setValorunitario(BigDecimal valorunitario) {
	this.valorunitario = valorunitario;
}

}
