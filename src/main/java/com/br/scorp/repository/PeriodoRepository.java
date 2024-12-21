package com.br.scorp.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.scorp.entity.Periodo;

@Repository
public interface PeriodoRepository extends CrudRepository<Periodo, Long>{
	@Query(value = "SELECT * FROM PERIODO ORDER BY ENDS_AT DESC LIMIT 1", nativeQuery = true)
	Periodo findMostRecent();
	
	Periodo findByStartsAt(LocalDate startsAt);
}
