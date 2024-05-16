package com.vinicius.atv002.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Integer> {

    FilmeEntity findByNome(String nome);

    List<FilmeEntity> findByGenero(String genero);

}
