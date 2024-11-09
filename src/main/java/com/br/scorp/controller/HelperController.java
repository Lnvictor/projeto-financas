package com.br.scorp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.scorp.entity.Menu;
import com.br.scorp.repository.MenuRepository;

@RestController
public class HelperController {
	@Autowired
	private MenuRepository repo;
	
	
	@PostMapping("/addMenu")
	public ResponseEntity<Menu> add(@RequestBody Menu menu) {
		this.repo.save(menu);
		
		return ResponseEntity.ok(menu);
	}
}
