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
	
	public List<Menu> getAllMenus(){
		List<Menu> menus = new ArrayList<Menu>();
		this.menuRepository.findAll().forEach(menus::add);
		
		return menus;
	}
	
	public Map<Long, List<Menu>> getSubMenus(List<Menu> menus){
		Map<Long, List<Menu>> map = new HashMap<Long, List<Menu>>();
		
		menus.forEach(menu -> {
			if (menu.getIsSubMenu()) {
				if (map.containsKey(menu.getIdParentMenu().longValue())) {
					map.get(menu.getIdParentMenu().longValue()).add(menu);
				}
				else {
					var l = new ArrayList<Menu>();
					l.add(menu);
					map.put(menu.getIdParentMenu().longValue(), l);
				}
			}
		});
		
		return map;
	}
}
