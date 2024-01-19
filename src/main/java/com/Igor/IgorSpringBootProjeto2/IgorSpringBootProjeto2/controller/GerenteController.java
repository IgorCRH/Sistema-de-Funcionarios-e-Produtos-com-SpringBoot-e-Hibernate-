package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.controller;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.DAO.FuncionariosDAO;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Cargo;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Funcionarios;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    private final FuncionariosDAO funcionariosDAO;
    private final EntityManager entityManager;
    private final FuncionarioService funcionarioService;

    public GerenteController(FuncionariosDAO funcionariosDAO, EntityManager entityManager, FuncionarioService funcionarioService) {
        this.funcionariosDAO = funcionariosDAO;
        this.entityManager = entityManager;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/cadastrarfuncionario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("funcionario", new Funcionarios());
        return "cadastrarFuncionario";
    }

    @PostMapping("/cadastrarfuncionario")
    public RedirectView cadastrarFuncionario(@RequestParam String nome,
                                             @RequestParam String cpf,
                                             @RequestParam Double salario,
                                             @RequestParam Long idcargo,
                                             Model model) {
        RedirectView redirectView = new RedirectView("/telagerente.html", true);

        try {
            Cargo cargo = funcionariosDAO.getCargoPorId(idcargo);
            if (cargo == null) {
                throw new IllegalArgumentException("Cargo não encontrado com o ID fornecido");
            }

            Funcionarios funcionario = new Funcionarios(nome, cpf, salario, cargo);

            funcionariosDAO.adicionarFuncionario(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
            // Adiciona uma mensagem de erro como parâmetro de consulta
            redirectView.addStaticAttribute("errorMessage", "Erro ao cadastrar o funcionário. Tente novamente.");
        }

        return redirectView;
    }



    @GetMapping("/telagerente")
    public String mostrarTelaGerente() {
        return "telagerente";
    }
    
    @GetMapping("/demissaofuncionario")
    public String mostrarFormularioDemissao() {
        return "demissaoFuncionario";
    }

    @PostMapping("/demitirFuncionario")
    public RedirectView demitirFuncionario(@RequestParam String cpffuncdemissao, Model model) {
        RedirectView redirectView = new RedirectView("/telagerente.html", true);

        try {
            Funcionarios funcionario = funcionarioService.findbyCPF(cpffuncdemissao);
            if (funcionario == null) {
                throw new IllegalArgumentException("Funcionário não encontrado com o CPF fornecido");
            }

            funcionariosDAO.remover(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
            // Adiciona uma mensagem de erro como parâmetro de consulta
            redirectView.addStaticAttribute("errorMessage", "Erro ao demitir o funcionário. Tente novamente.");
        }

        return redirectView;
    }
    
    @GetMapping("/atualizacaoSalario")
    public String mostrarFormularioAtualizacaoSalario() {
        return "atualizacaoSalario";
    }

    @PostMapping("/atualizacaoSalario")
    public RedirectView atualizarSalario(@RequestParam String cpffuncatualizaSalario,
                                         @RequestParam Double novovalorSalario,
                                         Model model) {
        RedirectView redirectView = new RedirectView("/telagerente.html", true);

        try {
            Funcionarios funcionario = funcionarioService.findbyCPF(cpffuncatualizaSalario);
            if (funcionario == null) {
                throw new IllegalArgumentException("Funcionário não encontrado com o CPF fornecido");
            }

            funcionariosDAO.atualizarSalario(funcionario, novovalorSalario);
        } catch (Exception e) {
            e.printStackTrace();
            // Adiciona uma mensagem de erro como parâmetro de consulta
            redirectView.addStaticAttribute("errorMessage", "Erro ao atualizar o salário do funcionário. Tente novamente.");
        }

        return redirectView;
    }
    
    @GetMapping("/listarFuncionarios")
    public Map<String, Object> listarFuncionarios(HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        List<Funcionarios> funcionarios = funcionarioService.listarTodos();

        if (!funcionarios.isEmpty()) {
            response.put("funcionarios", funcionarios);
        } else {
            response.put("mensagem", "Nenhum funcionário encontrado.");
        }

        return response;
    }
    
    @GetMapping("/pesquisarPorNome")
    public Map<String, Object> pesquisarPorNome(@RequestParam String nome) {
        Map<String, Object> response = new HashMap<>();

        try {
            Funcionarios funcionario = funcionarioService.pesquisarPorNome(nome);

            if (funcionario != null) {
                List<Funcionarios> funcionarios = new ArrayList<>();
                funcionarios.add(funcionario);
                response.put("funcionarios", funcionarios);
            } else {
                response.put("mensagem", "Nenhum funcionário encontrado com o CPF fornecido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("erro", "Erro ao pesquisar funcionário por CPF. Tente novamente.");
        }

        return response;
    }
    
    @GetMapping("/pesquisarPorCPF")
    public Map<String, Object> pesquisarPorCPF(@RequestParam String cpf) {
        Map<String, Object> response = new HashMap<>();

        try {
            Funcionarios funcionario = funcionarioService.findbyCPF(cpf);

            if (funcionario != null) {
                List<Funcionarios> funcionarios = new ArrayList<>();
                funcionarios.add(funcionario);
                response.put("funcionarios", funcionarios);
            } else {
                response.put("mensagem", "Nenhum funcionário encontrado com o CPF fornecido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("erro", "Erro ao pesquisar funcionário por CPF. Tente novamente.");
        }

        return response;
    }

}
