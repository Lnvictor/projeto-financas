package com.br.scorp.dto;

import java.math.BigDecimal;

import com.br.scorp.enums.SimulaEtapas;

public class AddNewSimulaForm {
    private Long id;
    private Integer meses;
    private BigDecimal minimo;
    private BigDecimal maximo;
    private BigDecimal quantidadeAlvo;
    private SimulaEtapas etapa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMeses() {
        return meses;
    }

    public void setMeses(Integer meses) {
        this.meses = meses;
    }

    public BigDecimal getMinimo() {
        return minimo;
    }

    public void setMinimo(BigDecimal minimo) {
        this.minimo = minimo;
    }

    public BigDecimal getMaximo() {
        return maximo;
    }

    public void setMaximo(BigDecimal maximo) {
        this.maximo = maximo;
    }

    public BigDecimal getQuantidadeAlvo() {
        return quantidadeAlvo;
    }

    public void setQuantidadeAlvo(BigDecimal quantidadeAlvo) {
        this.quantidadeAlvo = quantidadeAlvo;
    }

    public SimulaEtapas getEtapa() {
        return etapa;
    }

    public void setEtapa(SimulaEtapas etapa) {
        this.etapa = etapa;
    }
}
