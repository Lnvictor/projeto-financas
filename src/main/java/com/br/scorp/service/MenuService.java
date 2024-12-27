package com.br.scorp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.scorp.entity.Menu;
import com.br.scorp.repository.MenuRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public List<Menu> getAllMenus() {
		List<Menu> menus = new ArrayList<Menu>();
		this.menuRepository.findAll().forEach(menus::add);

		return menus;
	}

	public Map<Long, List<Menu>> getSubMenus(List<Menu> menus) {
		Map<Long, List<Menu>> map = new HashMap<Long, List<Menu>>();

		menus.forEach(menu -> {
			if (menu.getIsSubMenu()) {
				if (map.containsKey(menu.getIdParentMenu().longValue())) {
					map.get(menu.getIdParentMenu().longValue()).add(menu);
				} else {
					List<Menu> subMenus = new ArrayList<Menu>();
					subMenus.add(menu);
					map.put(menu.getIdParentMenu().longValue(), subMenus);
				}
			}
		});

		return map;
	}

	public Menu findMenuByLink(String link) {
		int index = link.indexOf("?");

		if (index > -1) {
			link = link.substring(link.indexOf("?"));
		}

		return this.menuRepository.findByLink(link);
	}

	public Menu findMenuByLinkOrDefault(String link) {
		Menu menu = this.findMenuByLink(link);

		if (menu == null)
			return this.findMenuByLink("/compras");

		return menu;
	}
}
