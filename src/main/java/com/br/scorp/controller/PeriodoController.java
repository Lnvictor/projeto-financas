package com.br.scorp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.scorp.entity.Menu;
import com.br.scorp.entity.Periodo;
import com.br.scorp.service.MenuService;
import com.br.scorp.service.PeriodoService;

@RequestMapping(name = "periodos", path = "periodos")
@Controller
public class PeriodoController {
	@Autowired
	private MenuService menuService;
	
	@Autowired PeriodoService periodoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String addNewPeriodo(Model model) {
		List<Menu> allMenus = this.menuService.getAllMenus();
		List<Menu> subMenus = this.menuService.getSubMenus(allMenus).get(allMenus.get(0).getId());
		List<Menu> root = allMenus.stream().filter(t -> !t.getIsSubMenu()).collect(Collectors.toList());
		
		List<Periodo> periodos = this.periodoService.getAllPeriodosRegistered();
		
		model.addAttribute("periodos", periodos);
		model.addAttribute("menus", root);
		model.addAttribute("submenus", subMenus);
		
		return "periodos/index.html";
	}
}
