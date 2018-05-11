package trabalho;

import java.util.List;

public class Departamento {
	private String nome;
	private List docentes;
	
	public Departamento() {
		nome = null;
		docentes = new ArrayList();
	}

	public String getNome() {
		return this.nome;
	}
}
