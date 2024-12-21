package com.br.scorp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.scorp.dto.AddNewCompraForm;
import com.br.scorp.entity.Compra;
import com.br.scorp.entity.Fatura;
import com.br.scorp.entity.Periodo;
import com.br.scorp.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	private CompraRepository repository;
	@Autowired
	private PeriodoService periodoService;
	@Autowired
	private FaturaService faturaService;

	public void criaOuAtualizaCompra(AddNewCompraForm form) {

		Compra compra;

		if (form.getIdCompra() != null && !form.getIdCompra().isEmpty()) {
			compra = this.repository.findById(Long.valueOf(form.getIdCompra())).get();

			if (compra != null) {
				this.atualizaCompra(compra, form);
				return;
			}
		}

		compra = new Compra();
		compra.setBanco(form.getBanco());
		compra.setDescricao(form.getDescricao());
		compra.setValor(form.getValor());

		Periodo periodo = this.periodoService.getMostRecentPeriodo();
		Fatura fatura = this.faturaService.findOrCreateByPeriodo(periodo);
		compra.setFatura(fatura);

		this.faturaService.updateFaturaValue(fatura, compra);

		this.repository.save(compra);
	}

	private void atualizaCompra(Compra compra, AddNewCompraForm form) {
		BigDecimal valorAnterior = compra.getValor();

		compra.setBanco(form.getBanco());
		compra.setDescricao(form.getDescricao());
		compra.setValor(form.getValor());

		Fatura faturaDaCompra = compra.getFatura();

		if (faturaDaCompra != null) {
			BigDecimal diferenca = compra.getValor().subtract(valorAnterior);

			this.faturaService.updateFaturaValue(faturaDaCompra, diferenca);
		}

		this.repository.save(compra);
	}

	public List<Compra> getComprasForPeriodo(Periodo periodo) {
		return null;
	}

	public List<Compra> getAllComprasFromFatura(Fatura faturaSelected) {
		List<Compra> comprasUnicas = this.repository.findByFatura(faturaSelected);

		return comprasUnicas;
	}

	public Compra getCompra(Long compraId) {
		return this.repository.findById(compraId).get();
	}

	public void deletarCompra(AddNewCompraForm form) {
		Compra compra = this.getCompra(Long.valueOf(form.getIdCompra()));
		Fatura faturaDaCompra = compra.getFatura();
		BigDecimal inversor = new BigDecimal(-1);
		
		if (faturaDaCompra != null) {
			this.faturaService.updateFaturaValue(faturaDaCompra, compra.getValor().multiply(inversor));
		}
		
		this.repository.delete(compra);
	}
}
