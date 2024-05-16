package com.vinicius.atv002.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "Filmes")
public class FilmeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "Nome do filme obrigatório")
    private String nome;
    @NotNull(message = "Sinópse do filme obrigatório")
    private String sinopse;
    @NotNull(message = "Gênero do filme obrigatório")
    private String genero;
    @DateTimeFormat
    private String data;
    @Valid
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL) // Talvez tenha que mudar isto
    private List<AvaliacaoEntity> avaliacoes = new ArrayList<AvaliacaoEntity>();
}
