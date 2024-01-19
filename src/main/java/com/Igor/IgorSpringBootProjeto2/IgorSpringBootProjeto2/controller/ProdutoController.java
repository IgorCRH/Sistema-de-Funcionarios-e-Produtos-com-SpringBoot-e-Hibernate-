package com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.DAO.ProdutosDAO;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Funcionarios;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.model.Produtos;
import com.Igor.IgorSpringBootProjeto2.IgorSpringBootProjeto2.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {


    @Autowired
    private final ProdutosDAO produtosDAO;
    private final EntityManager entityManager;
    private final ProdutoService produtoService;

    public ProdutoController(ProdutosDAO produtosDAO, EntityManager entityManager, ProdutoService produtoService) {
        this.produtosDAO = produtosDAO;
        this.entityManager = entityManager;
        this.produtoService = produtoService;
    }

    @GetMapping("/cadastrarProduto")
    public String mostrarFormulario(Model model) {
        model.addAttribute("produto", new Produtos());
        return "cadastrarProduto";
    }

    @PostMapping("/cadastrarProduto")
    public RedirectView cadastrarProduto(@RequestParam String nome,
            @RequestParam int quantidade,
            @RequestParam BigDecimal valorunitario) {

       try {
           Produtos produto = new Produtos(nome, quantidade, valorunitario);
           produtosDAO.adicionarProduto(produto);

            return new RedirectView("/telafuncionario.html", true);
            } catch (Exception e) {

            return new RedirectView("/cadastrarProduto.html", true);
            }
} 

    @GetMapping("/telafuncionario")
    public String mostrarTelaFuncionario() {
        return "telafuncionario";
    }
    
    @GetMapping("/removerProduto")
    public String mostrarFormularioRemocao() {
        return "removerProduto";
    }

    @PostMapping("/removerProduto")
    public RedirectView removerProduto(@RequestParam Long idprodremocao, Model model) {
        try {
            Optional<Produtos> produtoOpt = produtoService.pesquisarPorId(idprodremocao);

            if (produtoOpt.isPresent()) {
                Produtos produto = produtoOpt.get();
                produtosDAO.remover(produto);

                return new RedirectView("/telafuncionario.html", true);
            } else {
                throw new IllegalArgumentException("Produto não encontrado com o ID fornecido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao remover o produto. Tente novamente.");

            return new RedirectView("/telafuncionario.html", true);
        }
    }
    
    @GetMapping("/atualizarQuantidade")
    public String mostrarFormularioAtualizacaoQuantidade() {
        return "atualizarQuantidade";
    }
    
    @PostMapping("/atualizarQuantidade")
    public RedirectView atualizarQuantidade(@RequestParam String nomeatualizaqtd,
                                            @RequestParam int novovalorqtd,
                                            Model model) {
        try {
            Produtos produto = produtoService.pesquisarPorNome(nomeatualizaqtd);
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado com o CPF fornecido");
            }

            produtosDAO.atualizarQuantidade(produto, novovalorqtd);

            // Redirecionamento para a página desejada
            return new RedirectView("/telafuncionario.html", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao atualizar a quantidade do produto. Tente novamente.");

            // Redirecionamento para a página desejada
            return new RedirectView("/telafuncionario.html", true);
        }
    }
    
    @GetMapping("/atualizarValorUnitario")
    public String mostrarFormularioAtualizacaoValorUnitario() {
        return "atualizarValorUnitario";
    }
    
    @PostMapping("/atualizarValorUnitario")
    public RedirectView atualizarValorUnitario(@RequestParam String nomeatualizavalor,
                                               @RequestParam BigDecimal novovalorunitario,
                                               Model model) {
        try {
            Produtos produto = produtoService.pesquisarPorNome(nomeatualizavalor);
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado com o CPF fornecido");
            }

            produtosDAO.atualizarValorUnitario(produto, novovalorunitario);

            return new RedirectView("/telafuncionario.html", true);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao atualizar o valor unitário do produto. Tente novamente.");

            return new RedirectView("/telafuncionario.html", true);
        }
    }
    
    @GetMapping("/listarProdutos")
    public Map<String, Object> listarProdutos() {
        Map<String, Object> response = new HashMap<>();

        List<Produtos> produtos = produtoService.listarTodos();

        if (!produtos.isEmpty()) {
            response.put("produtos", produtos);
        } else {
            response.put("mensagem", "Nenhum produto encontrado.");
        }

        return response;
    }
    
    @GetMapping("/pesquisarPorNome")
    public Map<String, Object> pesquisarPorNome(@RequestParam String nomeProduto) {
        Map<String, Object> response = new HashMap<>();

        try {
            Produtos produto = produtoService.pesquisarPorNome(nomeProduto);

            if (produto != null) {
                List<Produtos> produtos = new ArrayList<>();
                produtos.add(produto);
                response.put("produtos", produtos);
            } else {
                response.put("mensagem", "Nenhum produto encontrado com o nome fornecido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("erro", "Erro ao pesquisar produto por nome. Tente novamente.");
        }

        return response;
    }
    
    @GetMapping("/pesquisarPorID")
    public Map<String, Object> pesquisarPorID(@RequestParam Long idProduto) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Produtos> produtoOpt = produtoService.pesquisarPorId(idProduto);

            if (produtoOpt.isPresent()) {
                Produtos produto = produtoOpt.get();
                List<Produtos> produtos = new ArrayList<>();
                produtos.add(produto);
                response.put("produtos", produtos);
            } else {
                response.put("mensagem", "Nenhum produto encontrado com o ID fornecido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("erro", "Erro ao pesquisar produto por ID. Tente novamente.");
        }

        return response;
    }

}
