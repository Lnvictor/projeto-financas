package com.br.scorp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.scorp.entity.Compra;
import com.br.scorp.entity.Fatura;
import com.br.scorp.entity.Periodo;
import com.br.scorp.service.CompraService;
import com.br.scorp.service.FaturaService;
import com.br.scorp.service.PeriodoService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {
	@Autowired
	private PeriodoService periodoService;
	@Autowired
	private FaturaService faturaService;
	@Autowired
	private CompraService compraService;

	
	@RequestMapping(method=RequestMethod.GET, name = "")
	public String index(Model model, @RequestParam(required = false) String dateSelected) {
		Periodo periodoSelected = this.periodoService.parsePeriodoSelected(dateSelected);
		Fatura faturaSelected = this.faturaService.findOrCreateByPeriodo(periodoSelected);
		List<Compra> compras =this.compraService.getAllComprasFromFatura(faturaSelected);
		
		model.addAttribute("fatura", faturaSelected);
		model.addAttribute("compras", compras);
		model.addAttribute("dateSelected", periodoSelected.getStartsAt());

		return "index";
	}
}
