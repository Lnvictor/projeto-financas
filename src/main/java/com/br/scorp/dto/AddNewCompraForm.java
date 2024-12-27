package com.br.scorp.dto;

import java.math.BigDecimal;

public class AddNewCompraForm {
	private String idCompra;
	public String banco;
	public String descricao;
	public BigDecimal valor;
	public String fatura;

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFatura() {
		return fatura;
	}

	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	public String getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(String idCompra) {
		this.idCompra = idCompra;
	}
}
