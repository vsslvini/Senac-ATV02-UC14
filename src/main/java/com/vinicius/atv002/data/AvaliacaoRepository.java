package com.vinicius.atv002.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Integer> {

    List<AvaliacaoEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM avaliacoes WHERE filme_id = :filmeId", nativeQuery = true)
    List<AvaliacaoEntity> findByFilmeId(@Param("filmeId") Integer filmeId);

}
