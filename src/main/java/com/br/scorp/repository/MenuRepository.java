package com.br.scorp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.scorp.entity.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long>{
	
}
