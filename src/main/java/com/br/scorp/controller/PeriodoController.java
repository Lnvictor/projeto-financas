package com.br.scorp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.scorp.dto.AddPeriodoForm;
import com.br.scorp.entity.Periodo;
import com.br.scorp.service.CompraService;
import com.br.scorp.service.PeriodoService;

@RequestMapping(name = "periodos", path = "periodos")
@Controller
public class PeriodoController {
	@Autowired
	PeriodoService periodoService;

	@Autowired
	CompraService compraService;

	@RequestMapping(method = RequestMethod.GET)
	public String addNewPeriodo(Model model) {
		return "periodos/index.html";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/add")
	public String addNewPeriodoFormHandler(AddPeriodoForm form) {
		Periodo periodo = periodoService.addPeriodo(form);

		if (form.getRecorrencia()) {
			compraService.salvarComprasRecorrentesNoPeriodo(periodo.getId());
		}

		return "redirect:/";
	}
}
