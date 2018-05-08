package trabalho;

public class Docente extends Pessoa{
	private int codigo;
	private String nome;
	private String departamento;

	public Docente() {
		codigo = 0;
		departamento = nome = null;
	}

	public void preencheDocente(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.nome = celulas[1];
		this.departamento = celulas[2];
	}
	public void qSort(Docente[] vetDocente, int tamVet) {
		
	}
	public void buscaDocenteVetDocentes() {
		
	}

}
