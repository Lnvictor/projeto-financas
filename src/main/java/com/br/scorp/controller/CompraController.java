package com.br.scorp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.scorp.dto.AddNewCompraForm;
import com.br.scorp.entity.Compra;
import com.br.scorp.service.CompraService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "compras")
public class CompraController {
	@Autowired
	private CompraService compraService;

	@RequestMapping
	public String addNewCompra(Model model) {
		return "compras/index";
	}

	@RequestMapping(path = "recorrentes", method = RequestMethod.GET)
	public String getRecorrentes(Model model) {
		List<Compra> comprasRecorrentes = this.compraService.getComprasRecorrentes();
		model.addAttribute("compras", comprasRecorrentes);

		return "compras/recorrentes";
	}

	@RequestMapping(method = RequestMethod.GET, path = "delete")
	public String deleteCompra(AddNewCompraForm form) {
		this.compraService.deletarCompra(form);

		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, path = "details")
	public String compraDetails(Model model, @RequestParam(required = true) Long secret) {
		Compra compra = this.compraService.getCompra(secret);

		model.addAttribute("compra", compra);

		return "compras/detalhe";
	}

	@RequestMapping(method = RequestMethod.POST, path = "add")
	public String addNewCompraFormHander(HttpServletRequest request, AddNewCompraForm form) {
		this.compraService.criaOuAtualizaCompra(form);

		return "redirect:/";
	}
}
