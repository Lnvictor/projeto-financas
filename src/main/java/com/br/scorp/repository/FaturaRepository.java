package com.br.scorp.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.scorp.entity.Fatura;
import com.br.scorp.entity.Periodo;

public interface FaturaRepository extends CrudRepository<Fatura, Long>{
	Fatura findByPeriodo(Periodo periodo);
}
