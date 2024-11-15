package com.br.scorp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.scorp.entity.Periodo;

@Repository
public interface PeriodoRepository extends CrudRepository<Periodo, Long>{

}
