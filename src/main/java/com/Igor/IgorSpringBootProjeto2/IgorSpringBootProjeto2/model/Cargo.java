package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCargo")
	private long IDCargo;
	
	@Column(name = "nome_cargo")
	private String nomecargo;
	
    @OneToMany(mappedBy = "cargo")
    @JsonManagedReference
    private List<Funcionarios> funcionarios;
	
	public Cargo(long ID, String nome) {
		super();
		this.IDCargo = ID;
		this.nomecargo = nome;
	}

	public Cargo() {
		super();
	}
	
    public long getID() {
		return IDCargo;
	}


	public void setID(long iD) {
		IDCargo = iD;
	}


	public String getNome() {
		return nomecargo;
	}


	public void setNome(String nome) {
		this.nomecargo = nome;
	}


	public void setFuncionarios(List<Funcionarios> funcionarios) {
		this.funcionarios = funcionarios;
	}


	public List<Funcionarios> getFuncionarios() {
        return funcionarios;
    }
	
}
