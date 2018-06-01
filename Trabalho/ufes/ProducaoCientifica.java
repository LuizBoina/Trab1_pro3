package ufes;

import java.io.Serializable;

public class ProducaoCientifica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoDocente;
	private String titulo;
	private boolean qualificada;

	public ProducaoCientifica(String[] celulas) throws NumberFormatException {
		this.codigoDocente = celulas[0];
		this.titulo = celulas[1];
		try {// melhorar isso talvez
			if (celulas[2].equals("X"))
				this.qualificada = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.qualificada = false;
		}
	}

	public String getString() {
		return this.titulo;
	}

	public boolean ehQualificada() {
		return this.qualificada;
	}

	public String getCodigoDocente() {
		return this.codigoDocente;
	}
}
