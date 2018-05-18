package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Docente {
	List<ProducaoCientifica> prodCientificas;
	private String nome;
	private int codigo;
	private String departamento;
	private List<Disciplina> disciplinasDadas;
	
	public Docente(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.nome = celulas[1];
		this.departamento = celulas[2];
		this.prodCientificas = new ArrayList<ProducaoCientifica>();
		this.disciplinasDadas = new ArrayList<Disciplina>();
	}

	public String getDepartamento() {
		return this.departamento;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public void adicionaDisciplinaALista(Disciplina disciplina) {
		this.disciplinasDadas.add(disciplina);
	}
	
	//metodo que retorna o Curso a partir da disciplina dada
}
