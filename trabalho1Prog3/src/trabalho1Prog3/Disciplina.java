package trabalho1Prog3;

public class Disciplina {
	private int codigo;
	private  String nome;
	private int codigoDocente;
	private int cHSemanal;
	private int cHSemestral;
	private int codigoCurso;

	public Disciplina(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0]);
			this.codigoDocente = Integer.parseInt(celulas[2]);
			this.cHSemanal = Integer.parseInt(celulas[3]);
			this.cHSemestral = Integer.parseInt(celulas[4]);
			this.codigoCurso = Integer.parseInt(celulas[5]);

		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.nome = celulas[1];
	}
	
	public int getCodigoCurso() {
		return this.codigoCurso;
	}
	
	public int getCodigoDocente() {
		return this.codigoDocente;
	}
	
	public int getcHSemanal() {
		return this.cHSemanal;
	}
	
	public int getcHSemestral() {
		return this.cHSemestral;
	}
}
