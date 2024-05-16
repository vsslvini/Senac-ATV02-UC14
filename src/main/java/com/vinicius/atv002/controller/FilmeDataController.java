package com.vinicius.atv002.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vinicius.atv002.data.AvaliacaoEntity;
import com.vinicius.atv002.data.FilmeEntity;
import com.vinicius.atv002.services.AvaliacaoService;
import com.vinicius.atv002.services.FilmeServices;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/filmes")
public class FilmeDataController {
    @Autowired
    FilmeServices filmeServices;
    @Autowired
    AvaliacaoService avaliacaoService;

    @GetMapping("/listar")
    public ResponseEntity<List<FilmeEntity>> getALLFilme() {
        List<FilmeEntity> filmes = filmeServices.ListarTodosFilmes();
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    @GetMapping("/listar-avaliacoes")
    public ResponseEntity<List<AvaliacaoEntity>> getAllAvaliacoes() {
        List<AvaliacaoEntity> avaliacoes = avaliacaoService.listatTodasAvaliacoes();
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> addFilme(@Valid @RequestBody FilmeEntity filme) {
        var novoFilme = filmeServices.criarFilme(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Integer id, @RequestBody FilmeEntity filme) {
        var filmeAtualizado = filmeServices.atualizarFilme(id, filme);
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }

    @PostMapping("/{filmeId}/avaliar")
    public ResponseEntity<FilmeEntity> avaliarFilme(@PathVariable Integer filmeId,
            @Valid @RequestBody AvaliacaoEntity avaliacao) {
        AvaliacaoEntity novaAvaliacao = avaliacaoService.criarAvaliacao(avaliacao);
        FilmeEntity filmeAvaliado = filmeServices.avaliarFilme(filmeId, novaAvaliacao);
        return new ResponseEntity<>(filmeAvaliado, HttpStatus.OK);
    }
}
