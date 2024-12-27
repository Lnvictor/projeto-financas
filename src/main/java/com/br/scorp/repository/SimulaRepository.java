package com.br.scorp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.scorp.entity.Simulacao;

@Repository
public interface SimulaRepository extends CrudRepository<Simulacao, Long> {
    @Query(value = "SELECT * FROM SIMULACAO ORDER BY atualizado_em desc LIMIT 1", nativeQuery = true)
    Simulacao findMostRecentSimula();
}
