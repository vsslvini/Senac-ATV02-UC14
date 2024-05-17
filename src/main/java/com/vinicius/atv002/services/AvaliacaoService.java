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
        List<AvaliacaoEntity> avaliacoes = avaliacaoRepository.findAll();
        return avaliacoes;
    }

    public List<AvaliacaoEntity> listarAvaliacaoId(Integer id) {
        List<AvaliacaoEntity> avaliacoes = avaliacaoRepository.findByFilmeId(id);
        return avaliacoes;
    }
}
