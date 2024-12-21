package com.br.scorp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.scorp.dto.AddPeriodoForm;
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
	
	public Periodo addPeriodo(AddPeriodoForm form) {
		Periodo periodo = new Periodo();
		
		periodo.setStartsAt(form.getInicio());
		periodo.setEndsAt(form.getFim());
		periodo.setDueDate(form.getVencimento());
		
		this.repo.save(periodo);
		
		return periodo;
	}
	
	public Periodo getMostRecentPeriodo() {
		return this.repo.findMostRecent();
	}

	public Periodo parsePeriodoSelected(String dateSelected) {
		LocalDate date;
		if (dateSelected == null) {
			date = LocalDate.now();
			date = LocalDate.of(date.getYear(), date.getMonth(), 1);
		}
		else {			
			date = LocalDate.parse(dateSelected, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		Periodo periodo = this.repo.findByStartsAt(date);
		
		return periodo;
	}
}
