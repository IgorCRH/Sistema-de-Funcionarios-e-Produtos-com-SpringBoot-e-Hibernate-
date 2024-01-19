package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.DAO.FuncionariosDAO;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Funcionarios")
@EnableJpaRepositories

public class Funcionarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "Salario")
    private Double salario;

    @Transient
    private String role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDCargo")
    @JsonBackReference
    private Cargo cargo;

    @Transient
    private EntityManager entityManager;

    public Funcionarios(String nome, String cpf, Double salario, Cargo cargo) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.cargo = cargo;
    }

    public Funcionarios() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Cargo getCargo() {
        return cargo;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String getRole(EntityManager entityManager) {
        if (entityManager == null) {
            throw new IllegalStateException("EntityManager não foi configurado para Funcionarios");
        }

        Long idCargoGerente = FuncionariosDAO.getIDCargoPorNome(entityManager, "Gerente");

        if (cargo != null && cargo.getID() == idCargoGerente) {
            role = "Gerente";
        } else {
            role = "Funcionario";
        }

        return role;
    }


}
