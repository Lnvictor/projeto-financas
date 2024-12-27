package com.br.scorp.interceptors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.br.scorp.entity.Menu;
import com.br.scorp.entity.Periodo;
import com.br.scorp.service.MenuService;
import com.br.scorp.service.PeriodoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommonModeInterceptor implements HandlerInterceptor {
	@Autowired
	private MenuService menuService;

	@Autowired
	private PeriodoService periodoService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			List<Menu> allMenus = this.menuService.getAllMenus();
			Menu currentRootMenu = this.menuService.findMenuByLinkOrDefault(request.getRequestURI());
			List<Menu> subMenus = this.menuService.getSubMenus(allMenus).get(currentRootMenu.getId());

			List<Menu> root = allMenus.stream().filter(t -> !t.getIsSubMenu()).collect(Collectors.toList());
			List<Periodo> periodos = this.periodoService.getAllPeriodosRegistered();

			if (currentRootMenu.getIsSubMenu()) {
				Menu voltar = new Menu();

				Menu parent = allMenus.stream()
						.filter(menu -> menu.getId().equals(currentRootMenu.getIdParentMenu().longValue())).findFirst()
						.get();

				voltar.setDescription("\u2190 VOLTAR");
				voltar.setLink(parent.getLink());

				if (subMenus == null)
					subMenus = new ArrayList<Menu>();
				subMenus.add(voltar);
			}

			root.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
			modelAndView.addObject("periodos", periodos);
			modelAndView.addObject("menus", root);
			modelAndView.addObject("submenus", subMenus);
		}
	}
}
