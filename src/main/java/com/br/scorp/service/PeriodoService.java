package com.br.scorp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.scorp.entity.Periodo;
import com.br.scorp.repository.PeriodoRepository;

@Service
public class PeriodoService {
	@Autowired
	private PeriodoRepository repo;
	
	public List<Periodo> getAllPeriodosRegistered() {
		List<Periodo> list = new ArrayList<>();
		this.repo.findAll().iterator().forEachRemaining(list::add);
		
		return list;
	}
}
