package com.br.scorp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.scorp.entity.Menu;
import com.br.scorp.service.MenuService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {
	@Autowired
	private MenuService service;
	
	@RequestMapping(method=RequestMethod.GET ,name = "")
	public String index(Model model) {
		List<Menu> allMenus = this.service.getAllMenus();
		List<Menu> subMenus = this.service.getSubMenus(allMenus).get(allMenus.get(0).getId());
		
		List<Menu> root = allMenus.stream().filter(t -> !t.getIsSubMenu()).collect(Collectors.toList());
		
		model.addAttribute("menus", root);
		model.addAttribute("submenus", subMenus);

		return "index";
	}
}
