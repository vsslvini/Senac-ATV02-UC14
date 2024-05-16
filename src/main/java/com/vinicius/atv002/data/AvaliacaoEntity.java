package com.vinicius.atv002.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacoes")
public class AvaliacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Email(message = "E-mail obrigatório")
    private String email;
    @NotNull(message = "Análise obrigatória")
    private String analise;
    @JsonIgnore
    @ManyToOne // Mapeamento muitos para um (muitas avaliações para um filme)
    private FilmeEntity filme;

}
