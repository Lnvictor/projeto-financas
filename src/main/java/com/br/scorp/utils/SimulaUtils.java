package com.br.scorp.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.br.scorp.dto.AddNewSimulaForm;
import com.br.scorp.entity.Simulacao;
import com.br.scorp.enums.SimulaEtapas;

public class SimulaUtils {
    public static Simulacao convertFormIntoEntity(AddNewSimulaForm form) {
        if (form == null) {
            return null;
        }

        Simulacao s = new Simulacao();

        s.setId(form.getId());
        s.setMaximo(Optional.ofNullable(form.getMaximo()).orElse(BigDecimal.ZERO));
        s.setMinimo(Optional.ofNullable(form.getMinimo()).orElse(BigDecimal.ZERO));
        s.setMeses(form.getMeses());
        s.setQuantidadeAlvo(Optional.ofNullable(form.getQuantidadeAlvo()).orElse(BigDecimal.ZERO));
        s.setEtapa(form.getEtapa());
        s.setAtualizadoEm(LocalDateTime.now());

        return s;
    }

    public static Simulacao createBasicSimula() {
        Simulacao s = new Simulacao();

        s.setMaximo(BigDecimal.ZERO);
        s.setMinimo(BigDecimal.ZERO);
        s.setMeses(0);
        s.setQuantidadeAlvo(BigDecimal.ZERO);
        s.setEtapa(SimulaEtapas.TEMPO_EM_MESES);
        s.setAtualizadoEm(LocalDateTime.now());

        return s;
    }

    public static AddNewSimulaForm convertEntityIntoForm(Simulacao simula) {
        if (simula == null) {
            return null;
        }

        AddNewSimulaForm form = new AddNewSimulaForm();

        form.setId(simula.getId());
        form.setEtapa(simula.getEtapa());
        form.setMinimo(simula.getMinimo());
        form.setMaximo(simula.getMaximo());
        form.setMeses(simula.getMeses());
        form.setQuantidadeAlvo(simula.getQuantidadeAlvo());

        return form;
    }

    public static Simulacao updateEntity(Simulacao simulacao, AddNewSimulaForm form) {
        boolean updated = false;

        if (form.getMeses() != null) {
            updated = true;
            simulacao.setMeses(form.getMeses());
        }

        if (form.getMinimo() != null) {
            updated = true;
            simulacao.setMinimo(form.getMinimo());
        }

        if (form.getMaximo() != null) {
            updated = true;
            simulacao.setMaximo(form.getMaximo());
        }

        if (form.getQuantidadeAlvo() != null) {
            updated = true;
            simulacao.setQuantidadeAlvo(form.getQuantidadeAlvo());
        }

        if (form.getEtapa() != null) {
            updated = true;
            simulacao.setEtapa(form.getEtapa());
        }

        if (updated) {
            simulacao.setAtualizadoEm(LocalDateTime.now());
        }
        return simulacao;
    }
}
