package com.br.scorp.dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class SimulaResults {
    private BigDecimal minimo;
    private BigDecimal maximo;
    private BigDecimal qtdMinima;
    private BigDecimal qtdMaxima;

    public SimulaResults(BigDecimal minimo, BigDecimal maximo, BigDecimal qtdMinima, BigDecimal qtdMaxima) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.qtdMinima = qtdMinima;
        this.qtdMaxima = qtdMaxima;
    }

    public SimulaResults() {
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

    public BigDecimal getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(BigDecimal qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public BigDecimal getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(BigDecimal qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }

    public String getFormattedMinimo() {
        return this.getFormattedValue(this.minimo);
    }

    public String getFormattedMaximo() {
        return this.getFormattedValue(this.maximo);
    }

    public String getFormattedQtdMinima() {
        return this.getFormattedValue(this.qtdMinima);
    }

    public String getFormattedQtdMaxima() {
        return this.getFormattedValue(this.qtdMaxima);
    }

    private String getFormattedValue(BigDecimal valor) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        return format.format(valor);
    }
}
