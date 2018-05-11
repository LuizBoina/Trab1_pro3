package trabalho;

public class Docente{
	private String nome;
	private int codigo;
	private String departamento;
	private ProdCientifica[] prodCientificas;

	public Docente() {
		codigo = 0;
		nome = departamento = null;
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
	public String getDepartamento() {
		return this.departamento;
	}

}
