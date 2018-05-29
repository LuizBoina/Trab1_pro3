package trabalho1Prog3;

import java.io.Serializable;

public class ProducaoCientifica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigoDocente;
	private String titulo;
	private boolean qualificada;

	public ProducaoCientifica(String[] celulas) throws NumberFormatException{
		
		try {
			this.codigoDocente = Integer.parseInt(celulas[0]);
		} finally {
			this.titulo = celulas[1];
			if (celulas[2].equals("X"))
				this.qualificada = true;
		}
	}

	public boolean ehQualificada() {
		return this.qualificada;
	}

	public int getCodigoDocente() {
		return this.codigoDocente;
	}
}
