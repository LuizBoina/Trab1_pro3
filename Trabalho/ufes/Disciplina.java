package ufes;

import java.io.Serializable;

public class Disciplina implements Comparable<Disciplina>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nome;
	private String codigoDocente;
	private int cHSemanal;
	private int cHSemestral;
	private int codigoCurso;

	public Disciplina(String[] celulas) throws NumberFormatException {
		this.codigo = celulas[0];
		this.codigoDocente = celulas[2];
		this.cHSemanal = Integer.parseInt(celulas[3]);
		this.cHSemestral = Integer.parseInt(celulas[4]);
		this.codigoCurso = Integer.parseInt(celulas[5]);
		this.nome = celulas[1];
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public int getCodigoCurso() {
		return this.codigoCurso;
	}

	public int compareTo(Disciplina outraDisciplina) {
		return Integer.parseInt(this.codigo) - Integer.parseInt(outraDisciplina.codigo);
	}

	public String getCodigoDocente() {
		return this.codigoDocente;
	}

	public int getcHSemanal() {
		return this.cHSemanal;
	}

	public int getcHSemestral() {
		return this.cHSemestral;
	}
}
