package com.br.scorp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.scorp.entity.Compra;
import com.br.scorp.entity.Fatura;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long>{
	List<Compra> findByFatura(Fatura fatura);

	List<Compra> findByIsCompraRecorrente(Boolean isCompraRecorrente);
}
