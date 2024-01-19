package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Funcionarios;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.service.FuncionarioService;

@Controller
public class LoginController {
	@Autowired
    private final FuncionarioService funcionarioService;
	private final EntityManager entityManager;

    public LoginController(FuncionarioService funcionarioService, EntityManager entityManager) {
        this.funcionarioService = funcionarioService;
        this.entityManager = entityManager;
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String cpf, Model model) {
        Funcionarios funcionario = funcionarioService.findbyCPF(cpf);

        if (funcionario != null && funcionario.getCargo() != null) {

            if ("Gerente".equals(funcionario.getRole(entityManager))) {
                return "redirect:/telagerente.html"; 
            } else {
                return "redirect:/telafuncionario.html";
            }
        } else {
            System.out.println("Funcionario ou cargo é nulo.");
            model.addAttribute("error", "CPF não encontrado ou cargo não especificado. Tente novamente.");
            return "login";
        }
    }
}


