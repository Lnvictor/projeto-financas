package com.br.scorp.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.scorp.entity.Compra;
import com.br.scorp.entity.Fatura;
import com.br.scorp.entity.Periodo;
import com.br.scorp.repository.FaturaRepository;

@Service
public class FaturaService {
	@Autowired
	private FaturaRepository repo;

	public Fatura findOrCreateByPeriodo(Periodo periodo) {
		Fatura fatura = this.repo.findByPeriodo(periodo);

		if (fatura == null) {
			fatura = new Fatura();
			fatura.setPeriodo(periodo);
			fatura.setValor(BigDecimal.ZERO);
			this.repo.save(fatura);

			return fatura;
		}

		return fatura;
	}

	public void updateFaturaValue(Fatura fatura, Compra compra) {
		this.updateFaturaValue(fatura, compra.getValor());
	}

	public void updateFaturaValue(Fatura fatura, BigDecimal value) {
		fatura.setValor(fatura.getValor().add(value));
		this.repo.save(fatura);
	}
}
