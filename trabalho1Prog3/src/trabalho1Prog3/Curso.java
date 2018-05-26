package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Curso implements Comparable<Curso>{
	private List<Disciplina> disciplinas;
	private int codigo;
	private String nome;
	private boolean ehGrad;
	private List<Discente> discentes;

	public Curso(String[] celulas) {
		discentes = new ArrayList<Discente>();
		disciplinas = new ArrayList<Disciplina>();
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatacao");
		}
		this.nome = celulas[1];
		try {
			if (celulas[2].equals("X"))
				this.ehGrad = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.ehGrad = false;
		}
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int TotalHorasDeDocente(int codDocente) {
		int qtd = 0;
		for(Disciplina disci: disciplinas) {
			if(disci.getCodigoDocente() == codDocente)
				qtd+=disci.getcHSemestral();
		}
		return qtd;
	}
	
	public List<Discente> getDiscentes(){
		return this.discentes;
	}

	public int getCodigoCurso() {
		return this.codigo;
	}
	
	public List<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}

	public void adicionaDisciplinaNoCurso(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	public void adicionaDiscenteNoCurso(Discente discente) {
		discentes.add(discente);
	}
	
	public int compareTo(Curso outroCurso) {
		return this.nome.compareTo(outroCurso.nome);
	}

}
