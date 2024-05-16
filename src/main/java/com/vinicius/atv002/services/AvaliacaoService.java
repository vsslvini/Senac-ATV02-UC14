package com.vinicius.atv002.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.atv002.data.AvaliacaoEntity;
import com.vinicius.atv002.data.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoEntity criarAvaliacao(AvaliacaoEntity avaliacao) {
        avaliacao.setId(null);
        avaliacao.setFilme(null);
        avaliacaoRepository.save(avaliacao);
        return avaliacao;
    }

    public List<AvaliacaoEntity> listatTodasAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

}
