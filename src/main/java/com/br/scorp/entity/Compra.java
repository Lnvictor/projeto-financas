package com.br.scorp.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;
	private String banco;
	private BigDecimal valor;
	private Boolean isCompraRecorrente;

	@ManyToOne
	@JoinColumn(name = "fatura_id")
	private Fatura fatura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public Boolean getIsCompraRecorrente() {
		return isCompraRecorrente;
	}

	public void setIsCompraRecorrente(Boolean isCompraRecorrente) {
		this.isCompraRecorrente = isCompraRecorrente;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
