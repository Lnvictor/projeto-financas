package com.br.scorp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.scorp.dto.AddNewSimulaForm;
import com.br.scorp.dto.SimulaResults;
import com.br.scorp.entity.Simulacao;
import com.br.scorp.enums.SimulaEtapas;
import com.br.scorp.repository.SimulaRepository;
import com.br.scorp.utils.SimulaUtils;

@Service
public class SimulaService {
	@Autowired
	private SimulaRepository repository;

	public List<String> getSimulaEtapas() {
		List<String> resp = new ArrayList<String>();

		for (SimulaEtapas s : SimulaEtapas.values()) {
			if (!s.equals(SimulaEtapas.FINALIZADO))
				resp.add(s.toString());
		}

		return resp;
	}

	public AddNewSimulaForm getSimula(Long id) {
		Simulacao simula = this.repository.findById(id).get();
		return SimulaUtils.convertEntityIntoForm(simula);
	}

	public Simulacao criaSimulacaoDoZero() {
		Simulacao simula = SimulaUtils.createBasicSimula();
		return this.repository.save(simula);
	}

	public Simulacao salvarSimulacao(AddNewSimulaForm simula) {
		SimulaEtapas etapaAtual = simula.getEtapa();
		SimulaEtapas etapaSeguinte = Optional.ofNullable(SimulaEtapas.fromOrdinal(etapaAtual.getValue() + 1))
				.orElse(etapaAtual);
		simula.setEtapa(etapaSeguinte);

		Optional<Simulacao> simulaEOptional = this.repository.findById(simula.getId());

		if (simulaEOptional.isPresent()) {
			Simulacao simulaE = SimulaUtils.updateEntity(simulaEOptional.get(), simula);
			return this.repository.save(simulaE);
		}

		return null;
	}

	public AddNewSimulaForm buscarSimulacaoMaisRecente() {
		return SimulaUtils.convertEntityIntoForm(this.repository.findMostRecentSimula());
	}

	public String buscarFieldPorEtapa(SimulaEtapas etapa) {
		switch (etapa) {
			case TEMPO_EM_MESES:
				return "meses";
			case MINIMO_EM_REAIS:
				return "minimo";
			case MAXIMO_EM_REAIS:
				return "maximo";
			case QUANTIDADE_NECESSARIA:
				return "quantidadeAlvo";
			default:
				return null;
		}
	}

	public SimulaResults processarResultadoSimulacao(AddNewSimulaForm form) {
		form = this.getSimula(form.getId());

		BigDecimal minimo = form.getMinimo();
		BigDecimal maximo = form.getMaximo();
		BigDecimal meses = BigDecimal.valueOf(form.getMeses());

		BigDecimal totalMinimo = minimo.multiply(meses);
		BigDecimal totalMaximo = maximo.multiply(meses);

		this.finalizaSimulacao(form.getId());

		return new SimulaResults(minimo, maximo, totalMinimo, totalMaximo);
	}

	private void finalizaSimulacao(Long id) {
		Optional<Simulacao> simula = this.repository.findById(id);

		if (simula.isPresent()) {
			Simulacao simulaEntity = simula.get();
			simulaEntity.setEtapa(SimulaEtapas.FINALIZADO);
			simulaEntity.setAtualizadoEm(LocalDateTime.now());
			this.repository.save(simulaEntity);
		}
	}

    public void zerar() {
        this.repository.deleteAll();
    }
}
