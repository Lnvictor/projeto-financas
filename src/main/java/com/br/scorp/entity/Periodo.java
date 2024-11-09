package com.br.scorp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "periodo")
public class Periodo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private LocalDate startsAt;
	private LocalDate endsAt;
	private LocalDate dueDate;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public LocalDate getStartsAt() {
		return startsAt;
	}
	public void setStartsAt(LocalDate startsAt) {
		this.startsAt = startsAt;
	}
	public LocalDate getEndsAt() {
		return endsAt;
	}
	public void setEndsAt(LocalDate endsAt) {
		this.endsAt = endsAt;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
}
