package com.vinicius.atv002.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.vinicius.atv002.data.AvaliacaoEntity;
import com.vinicius.atv002.data.FilmeEntity;
import com.vinicius.atv002.data.Tema;
import com.vinicius.atv002.services.AvaliacaoService;
import com.vinicius.atv002.services.FilmeServices;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeWebController {
    @Autowired
    FilmeServices filmeServices;
    @Autowired
    AvaliacaoService avaliacaoService;

    List<FilmeEntity> filmes = new ArrayList<FilmeEntity>();
    List<AvaliacaoEntity> avaliacoes = new ArrayList<AvaliacaoEntity>();
    Tema tema = new Tema();

    @GetMapping("/")
    public String getMethodName(@CookieValue(name = "tema", defaultValue = "") String cookie, @ModelAttribute Tema tema,
            Model model, HttpServletResponse response) {

        if (cookie.isEmpty()) {
            // Define o tema padrão
            String temaPadrao = "claro";
            cookie = temaPadrao;

            Cookie cookieTema = new Cookie("tema", temaPadrao);
            cookieTema.setDomain("localhost");
            cookieTema.setMaxAge(7 * 24 * 60 * 60);

            response.addCookie(cookieTema);
        }

        model.addAttribute("listaFilmes", filmeServices.ListarTodosFilmes());
        model.addAttribute("tema", tema);
        model.addAttribute("css", cookie);

        return "index";
    }

    @PostMapping("/")
    public ModelAndView criarCookie(@CookieValue(name = "tema", defaultValue = "claro") String cookie,
            @ModelAttribute Tema tema, HttpServletResponse response) {
        String temaEscolhido;

        if ("claro".equals(cookie)) {
            temaEscolhido = "escuro";
        } else {
            temaEscolhido = "claro";
        }

        Cookie cookieTema = new Cookie("tema", temaEscolhido);
        cookieTema.setDomain("localhost");
        cookieTema.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(cookieTema);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/avaliacoes/{id}")
    public String verAvaliacoes(@PathVariable(value = "id") Integer id, Model model,
            @CookieValue(name = "tema", defaultValue = "claro") String cookie, Tema tema) {
        model.addAttribute("filme", filmeServices.getFilmeId(id));
        model.addAttribute("avaliacao", new AvaliacaoEntity());
        model.addAttribute("avaliacoes", avaliacaoService.listarAvaliacaoId(id));
        model.addAttribute("css", cookie);
        model.addAttribute("tema", tema);
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
    public String exibirCadastro(Model model, @CookieValue(name = "tema", defaultValue = "claro") String cookie,
            Tema tema) {
        model.addAttribute("filme", new FilmeEntity());
        model.addAttribute("css", cookie);
        model.addAttribute("tema", tema);
        return "cadastroFilme";
    }

    @PostMapping("/cadastroFilme")
    public String processarForm(@ModelAttribute FilmeEntity filme, Model model) {
        filmeServices.criarFilme(filme); // Aqui está adicionando o filme à lista filmes
        model.addAttribute("filme", filme);
        return "redirect:/";

    }

    @GetMapping("/deletarFilme/{id}")
    public String deletarFilme(@PathVariable(value = "id") Integer id) {
        filmeServices.deletarFilme(id);
        return "redirect:/";
    }
}
