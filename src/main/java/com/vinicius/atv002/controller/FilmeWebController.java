package com.vinicius.atv002.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.vinicius.atv002.services.FilmeServices;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmeWebController {
    @Autowired
    FilmeServices filmeServices;

    @GetMapping("/")
    public String getMethodName(Model model) {
        model.addAttribute("listaFilmes", filmeServices.ListarTodosFilmes());
        return "index";
    }

}
