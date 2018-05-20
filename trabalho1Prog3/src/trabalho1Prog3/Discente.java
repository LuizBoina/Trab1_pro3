package trabalho1Prog3;

public class Discente {
	private int matricula;
	private String nome;
	private int codigoCurso;

	public Discente(String[] celulas) {
		try {
			this.matricula = Integer.parseInt(celulas[0]);
			this.codigoCurso = Integer.parseInt(celulas[2]);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatacao");
		}
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
