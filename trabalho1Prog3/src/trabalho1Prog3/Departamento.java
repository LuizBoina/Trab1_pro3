package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Departamento {
	private String nome;
	private List<Docente> docentes;

	public Departamento(String nome) {
		this.nome = nome;
		this.docentes = new ArrayList<Docente>();
	}

	public String getNomeDepartamento() {
		return this.nome;
	}

	public void setNovoDocente(Docente novoDocente) {
		this.docentes.add(novoDocente);
	}

	public void ordenaPorNomeDocente() {
		// bubbleSort?
	}

	public int indexDocente
}