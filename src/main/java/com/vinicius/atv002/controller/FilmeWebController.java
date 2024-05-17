package com.vinicius.atv002.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.vinicius.atv002.data.AvaliacaoEntity;
import com.vinicius.atv002.data.FilmeEntity;
import com.vinicius.atv002.services.AvaliacaoService;
import com.vinicius.atv002.services.FilmeServices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeWebController {
    @Autowired
    FilmeServices filmeServices;
    @Autowired
    AvaliacaoService avaliacaoService;

    List<FilmeEntity> filmes = new ArrayList<FilmeEntity>();
    List<AvaliacaoEntity> avaliacoes = new ArrayList<AvaliacaoEntity>();

    @GetMapping("/")
    public String getMethodName(Model model) {
        model.addAttribute("listaFilmes", filmeServices.ListarTodosFilmes());
        return "index";
    }

    @GetMapping("/avaliacoes/{id}")
    public String verAvaliacoes(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("filme", filmeServices.getFilmeId(id));
        model.addAttribute("avaliacao", new AvaliacaoEntity());
        model.addAttribute("avaliacoes", avaliacaoService.listarAvaliacaoId(id));
        return "avaliar";
    }

    @PostMapping("/avaliacoes/{id}")
    public String processarAvaliacoes(@PathVariable(value = "id") Integer id, @ModelAttribute AvaliacaoEntity avaliacao,
            Model model) {
        avaliacao.setFilme(filmeServices.getFilmeId(id));
        avaliacaoService.criarAvaliacao(avaliacao);
        filmeServices.avaliarFilme(id, avaliacao);
        model.addAttribute("filme", filmeServices.getFilmeId(id));
        model.addAttribute("avaliacoes", avaliacaoService.listatTodasAvaliacoes());
        return "redirect:/";
    }

    @GetMapping("/cadastroFilme")
    public String exibirCadastro(Model model) {
        model.addAttribute("filme", new FilmeEntity());
        return "cadastroFilme";
    }

    @PostMapping("/cadastroFilme")
    public String processarForm(@ModelAttribute FilmeEntity filme, Model model) {
        filmeServices.criarFilme(filme); // Aqui está adicionando o filme à lista filmes
        model.addAttribute("filme", filme);
        return "redirect:/";

    }

    @GetMapping("/deletarFilme/{id}")
    public String  deletarFilme(@PathVariable(value = "id") Integer id ){
        filmeServices.deletarFilme(id);
        return "redirect:/";
    }
}
