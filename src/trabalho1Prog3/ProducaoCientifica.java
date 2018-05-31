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

	public ProducaoCientifica(String[] celulas) {
		
		try {
			this.codigoDocente = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatacao");
		}
		this.titulo = celulas[1];
		try {
			if (celulas[2].equals("X"))
				this.qualificada = true;
			else
				this.qualificada = false;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.qualificada = false;
		}

	}
	
	public boolean ehQualificada() {
		return this.qualificada;
	}

	public int getCodigoDocente() {
		return this.codigoDocente;
	}
}
