package com.vinicius.atv002.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.vinicius.atv002.data.AvaliacaoEntity;
import com.vinicius.atv002.data.AvaliacaoRepository;
import com.vinicius.atv002.data.FilmeEntity;
import com.vinicius.atv002.data.FilmeRepository;

@Service
public class FilmeServices {
    @Autowired
    FilmeRepository filmeRepository;
    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public FilmeEntity criarFilme(FilmeEntity filme) {
        filme.setId(null);
        filme.setAvaliacoes(null);
        filmeRepository.save(filme);
        return filme;
    }

    public List<FilmeEntity> ListarTodosFilmes() {
        return filmeRepository.findAll();
    }

    public FilmeEntity getFilmeId(Integer id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Filme não enconrtrado" + id));

    }

    public FilmeEntity atualizarFilme(Integer filmeId, FilmeEntity filmeRequeste) {
        FilmeEntity filme = getFilmeId(filmeId);
        filme.setNome(filmeRequeste.getNome());
        filme.setSinopse(filmeRequeste.getSinopse());
        filme.setGenero(filmeRequeste.getGenero());
        filme.setData(filmeRequeste.getData());
        filmeRepository.save(filme);
        return filme;
    }

    public FilmeEntity avaliarFilme(Integer filmeId, AvaliacaoEntity avaliacao) {
        FilmeEntity filme = getFilmeId(filmeId);
        filme.getAvaliacoes().add(avaliacao);
        avaliacao.setFilme(filme);
        avaliacaoRepository.save(avaliacao);
        return filme;
    }

}
