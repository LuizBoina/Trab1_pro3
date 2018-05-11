package trabalho;

public class Discente{
	private int matricula;
	private String nome;
	private int codigoCurso;
	
	public Discente() {
		nome = null;
		matricula = codigoCurso = 0;
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
