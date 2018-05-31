package trabalho1Prog3;

import java.io.Serializable;

public class OrientaGrad extends Orientacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigoCurso;

	public OrientaGrad(String[] celulas, Discente dis) throws NumberFormatException {
		super(celulas[0], celulas[3], dis);
		this.codigoCurso = Integer.parseInt(celulas[2]);
	}
	
	public int getCodigoCurso() {
		return this.codigoCurso;
	}

}
