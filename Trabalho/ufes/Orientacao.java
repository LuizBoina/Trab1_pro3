package ufes;

import java.io.Serializable;

public class Orientacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoDocente;
	protected Discente discente;
	private int CHsemanal;

	public Orientacao(String codigoDocente, String CHsemanal, Discente dis) throws NumberFormatException {
		this.codigoDocente = codigoDocente;
		this.CHsemanal = Integer.parseInt(CHsemanal);
		this.discente = dis;
	}

	public String getCodigoDocente() {
		return this.codigoDocente;
	}

	public String getNomeDiscente() {
		return this.discente.getNome();
	}

	public int getCHsemanal() {
		return this.CHsemanal;
	}
}
