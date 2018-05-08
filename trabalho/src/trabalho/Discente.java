package trabalho;

public class Discente extends Pessoa{
	private int matricula;
	private String nome;
	private int codigoCurso;
	
	public Discente() {
		matricula = codigoCurso = 0;
		nome = null;
	}
	
	public void preencheDiscente(String[] celulas) {
		try {
			this.matricula = Integer.parseInt(celulas[0]);
			this.codigoCurso = Integer.parseInt(celulas[2]);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		this.nome = celulas[1];
	}
}
