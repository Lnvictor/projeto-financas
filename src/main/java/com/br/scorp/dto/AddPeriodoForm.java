package com.br.scorp.dto;

import java.time.LocalDate;

public class AddPeriodoForm {
	public LocalDate inicio;
	public LocalDate fim;
	public LocalDate vencimento;
	public Boolean recorrencia;

	public Boolean getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(Boolean recorrencia) {
		this.recorrencia = recorrencia;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}
}
