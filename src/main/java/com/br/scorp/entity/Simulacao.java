package com.br.scorp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.scorp.enums.SimulaEtapas;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "simulacao")
public class Simulacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer meses;

    private BigDecimal minimo;
    private BigDecimal maximo;
    private BigDecimal quantidadeAlvo;

    private LocalDateTime atualizadoEm;

    @Enumerated(EnumType.ORDINAL)
    private SimulaEtapas etapa;

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public SimulaEtapas getEtapa() {
        return etapa;
    }

    public void setEtapa(SimulaEtapas etapa) {
        this.etapa = etapa;
    }

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
}
