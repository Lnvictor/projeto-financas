package com.br.scorp.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.scorp.dto.AddNewSimulaForm;
import com.br.scorp.dto.FaturaInfo;
import com.br.scorp.dto.SimulaResults;
import com.br.scorp.entity.Compra;
import com.br.scorp.entity.Fatura;
import com.br.scorp.entity.Periodo;
import com.br.scorp.repository.FaturaRepository;

@Service
public class FaturaService {
	@Autowired
	private FaturaRepository repo;
	@Autowired
	private SimulaService simulaService;

	public FaturaInfo findOrCreateByPeriodo(Periodo periodo) {
		Fatura fatura = this.repo.findByPeriodo(periodo);

		if (fatura == null) {
			fatura = new Fatura();
			fatura.setPeriodo(periodo);
			fatura.setValor(BigDecimal.ZERO);
			this.repo.save(fatura);
		}

		AddNewSimulaForm simulacaoMaisRecente = this.simulaService.buscarSimulacaoMaisRecente();
		BigDecimal diff;
		SimulaResults result;

		if (simulacaoMaisRecente == null) {
			diff = BigDecimal.ZERO;
			result = new SimulaResults(BigDecimal.ZERO, BigDecimal.ZERO,
					null, null);
		} else {
			diff = BigDecimal.valueOf(6300).subtract(fatura.getValor());
			result = new SimulaResults(simulacaoMaisRecente.getMinimo(), simulacaoMaisRecente.getMaximo(),
					null, null);
		}

		FaturaInfo info = new FaturaInfo(fatura, result, diff);

		return info;
	}

	public void updateFaturaValue(Fatura fatura, Compra compra) {
		this.updateFaturaValue(fatura, compra.getValor());
	}

	public void updateFaturaValue(Fatura fatura, BigDecimal value) {
		fatura.setValor(fatura.getValor().add(value));
		this.repo.save(fatura);
	}
}
