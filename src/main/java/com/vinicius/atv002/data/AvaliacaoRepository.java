package com.vinicius.atv002.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Integer> {

    List<AvaliacaoEntity> findByEmail(String email);
    
    
    
}
