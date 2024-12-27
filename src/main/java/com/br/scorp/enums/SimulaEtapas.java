package com.br.scorp.enums;

public enum SimulaEtapas {
	TEMPO_EM_MESES(0),
	MINIMO_EM_REAIS(1),
	MAXIMO_EM_REAIS(2),
	QUANTIDADE_NECESSARIA(3),
	FINALIZADO(4);

	private int value;

	SimulaEtapas(int value) {
		this.value = value;
	}

	public static SimulaEtapas fromString(String s) {
		return SimulaEtapas.valueOf(s);
	}

	public static SimulaEtapas fromOrdinal(int ordinal) {
		for (SimulaEtapas etapa : SimulaEtapas.values()) {
			if (etapa.getValue() == ordinal) {
				return etapa;
			}
		}

		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
