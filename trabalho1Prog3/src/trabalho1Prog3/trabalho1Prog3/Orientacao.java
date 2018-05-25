package trabalho1Prog3;

import java.io.Serializable;

public class Orientacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigoDocente;
	protected Discente discente;
	private int CHsemanal;
	
	public Orientacao(String codigoDocente, String CHsemanal, Discente dis) {
		try {
			this.codigoDocente = Integer.parseInt(codigoDocente);
			this.CHsemanal = Integer.parseInt(CHsemanal);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatacao");
		}
		this.discente = dis;
	}

	public int getCodigoDocente() {
		return this.codigoDocente;
	}
	
	public int getCHsemanal() {
		return this.CHsemanal;
	}
}