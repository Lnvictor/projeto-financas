package com.br.scorp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.scorp.dto.AddNewSimulaForm;
import com.br.scorp.dto.SimulaResults;
import com.br.scorp.enums.SimulaEtapas;
import com.br.scorp.service.SimulaService;

@Controller
@RequestMapping(path = "simula")
public class SimulaController {
	@Autowired
	private SimulaService service;

	@RequestMapping(method = RequestMethod.GET)
	public String simulaIndex(Model model) {
		List<String> etapas = this.service.getSimulaEtapas();
		model.addAttribute("etapas", etapas);
		AddNewSimulaForm simula = this.service.buscarSimulacaoMaisRecente();

		if (simula == null) {
			model.addAttribute("simulacaoInexistente", true);

		} else {
			if (simula.getEtapa().equals(SimulaEtapas.FINALIZADO)) {
				return "redirect:/simula/process?id=" + simula.getId();
			}

			model.addAttribute("simulacaoInexistente", false);
			model.addAttribute("etapaAtual", simula.getEtapa().toString());
			model.addAttribute("idSimula", simula.getId());
			model.addAttribute("campo", this.service.buscarFieldPorEtapa(simula.getEtapa()));
		}

		return "simula/index.html";
	}

	@RequestMapping(path = "addFakePost", method = RequestMethod.GET)
	public String fakePost() {
		this.service.criaSimulacaoDoZero();
		return "redirect:/simula";
	}

	@RequestMapping(path = "add", method = RequestMethod.POST)
	public String add(AddNewSimulaForm form) {
		this.service.salvarSimulacao(form);

		return "redirect:/simula";
	}

	@RequestMapping(path = "detail", method = RequestMethod.GET)
	public String detailView(@RequestParam String secret) {
		return "simula/detail.html";
	}

	@RequestMapping(path = "process", method = RequestMethod.GET)
	public String processSimula(Model model, AddNewSimulaForm form) {
		SimulaResults results = this.service.processarResultadoSimulacao(form);
		
		model.addAttribute("result", results);

		return "simula/results.html";
	}

	@RequestMapping(path = "/zerar", method=RequestMethod.GET)
	public String requestMethodName() {
		this.service.zerar();

		return "redirect:/simula";
	}
	
}
