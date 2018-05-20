package trabalho1Prog3;

public class OrientaGrad extends Orientacao {
	private int codigoCurso;
	
	public OrientaGrad(String[] celulas, Discente dis) {
		super(celulas[0], celulas[3], dis);
		try {
			this.codigoCurso = Integer.parseInt(celulas[2]);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatacao");
		}
	}
	
	
}
