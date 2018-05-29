package trabalho1Prog3;

public class OrientaGrad extends Orientacao {
	private int codigoCurso;

	public OrientaGrad(String[] celulas, Discente dis) throws NumberFormatException {
		super(celulas[0], celulas[3], dis);
		try {
			this.codigoCurso = Integer.parseInt(celulas[2]);
		} finally {
		}
	}

}
