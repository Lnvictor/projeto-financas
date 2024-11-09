package com.br.scorp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menus")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private String link;
	private Boolean isSubMenu;
	private Integer idParentMenu;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Boolean getIsSubMenu() {
		return isSubMenu;
	}
	public void setIsSubMenu(Boolean isSubMenu) {
		this.isSubMenu = isSubMenu;
	}
	public Integer getIdParentMenu() {
		return idParentMenu;
	}
	public void setIdParentMenu(Integer idParentMenu) {
		this.idParentMenu = idParentMenu;
	}
}
