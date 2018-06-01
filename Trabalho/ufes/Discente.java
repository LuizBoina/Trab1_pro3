package ufes;

import java.io.Serializable;

public class Discente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int matricula;
	private String nome;
	private int codigoCurso;

	public Discente(String[] celulas) throws NumberFormatException {
		this.matricula = Integer.parseInt(celulas[0]);
		this.codigoCurso = Integer.parseInt(celulas[2]);
		this.nome = celulas[1];
	}

	public int getMatricula() {
		return this.matricula;
	}

	public String getNome() {
		return nome;
	}

	public int getCodigoCurso() {
		return this.codigoCurso;
	}
}
