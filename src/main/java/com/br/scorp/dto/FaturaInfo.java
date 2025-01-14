package com.br.scorp.dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.br.scorp.entity.Fatura;

public class FaturaInfo {
    private Fatura fatura;
    private SimulaResults result;
    private BigDecimal difference;

    public FaturaInfo() {
    }

    public FaturaInfo(Fatura fatura, SimulaResults result, BigDecimal difference) {
        this.fatura = fatura;
        this.result = result;
        this.difference = difference;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    public SimulaResults getResult() {
        return result;
    }

    public void setResult(SimulaResults result) {
        this.result = result;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public String getFormattedDiff() {
        if (this.difference == null) {
            return null;
        }

        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.of("pt", "BR"));

        return fmt.format(this.difference);
    }
}
